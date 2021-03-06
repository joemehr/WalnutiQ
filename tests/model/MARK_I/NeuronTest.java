package model.MARK_I;

import model.MARK_I.Cell;
import model.MARK_I.DistalSegment;
import model.MARK_I.Neuron;
import model.MARK_I.Synapse;
import model.MARK_I.VisionCell;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version July 22, 2013
 */
public class NeuronTest extends junit.framework.TestCase {
    private Neuron neuron;

    public void setUp() {
	this.neuron = new Neuron();
    }

    public void test_getBestPreviousActiveSegment() {
	// previousActiveSegments size = 0
	assertNull(this.neuron.getBestPreviousActiveSegment());

	// previousActiveSegments size = 1 with no sequence Segments
	DistalSegment distalSegment_1 = new DistalSegment();
	distalSegment_1.setPreviousActiveState(true);
	this.neuron.addDistalSegment(distalSegment_1);
	assertEquals(distalSegment_1,
		this.neuron.getBestPreviousActiveSegment());

	// previousActiveSegments size = 2 with 0 sequence Segment
	VisionCell activeVisionCell_1 = new VisionCell();
	activeVisionCell_1.setPreviousActiveState(true);
	Synapse<Cell> previousActiveSynapse_1 = new Synapse<Cell>(activeVisionCell_1, 0.3, 0, 0);

	DistalSegment distalSegment_2 = new DistalSegment();
	distalSegment_2.addSynapse(previousActiveSynapse_1);
	distalSegment_2.setPreviousActiveState(true);
	this.neuron.addDistalSegment(distalSegment_2);
	assertEquals(distalSegment_2,
		this.neuron.getBestPreviousActiveSegment());

	// previousActiveSegments size = 3 with 1 sequence Segment
	DistalSegment distalSegment_3 = new DistalSegment();
	distalSegment_3.setPreviousActiveState(true);
	distalSegment_3.setSequenceState(true);
	this.neuron.addDistalSegment(distalSegment_3);
	assertEquals(distalSegment_3,
		this.neuron.getBestPreviousActiveSegment());

	// previousActiveSegments size = 3 with
	// 2 sequence Segments
	DistalSegment distalSegment_4 = new DistalSegment();
	distalSegment_4.addSynapse(previousActiveSynapse_1);
	distalSegment_4.setPreviousActiveState(true);
	distalSegment_4.setSequenceState(true);
	this.neuron.addDistalSegment(distalSegment_4);
	assertEquals(distalSegment_4,
		this.neuron.getBestPreviousActiveSegment());
    }
}
