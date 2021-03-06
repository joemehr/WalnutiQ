package model.theory;

import model.MARK_I.ColumnPosition;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents attention levels of Ideas within a Memory object due to
 * current input on a trained or untrained Region.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 18, 2013
 */
public class MemoryClassifier {
    private Set<ColumnPosition> regionOutput;
    private Memory memory;

    public MemoryClassifier(Memory memory) {
	if (memory == null) {
	    throw new IllegalArgumentException(
		    "memory in MemoryClassifier class constructor cannot be null");
	}
	this.regionOutput = new HashSet<ColumnPosition>();
	this.memory = memory;
    }

    /**
     * Update all Idea objects within Memory with any different ColumnPositions
     * that are becoming active. No ColumnPositions objects are removed from any
     * Idea objects.
     *
     * @param spatialPoolerOutput
     *            Active Columns of the Region object after executing the
     *            spatial pooler algorithm on it during one time step.
     */
    public void updateIdeas(Set<ColumnPosition> spatialPoolerOutput) {
	if (spatialPoolerOutput == null) {
	    throw new IllegalArgumentException(
		    "spatialPoolerOutput in MemoryClassifier class updateIdeas method cannot be null");
	}
	// update Region output with it's activeColumns
	this.regionOutput = spatialPoolerOutput;

	// compare which set of ColumnPositions in memories has the greatest
	// intersection with regionOutput
	Set<ColumnPosition> intersectionSet = new HashSet<ColumnPosition>(
		this.regionOutput);

	// iterate through all Ideas in Memory
	for (Idea idea : this.memory.getIdeas()) {
	    // intersection between two sets of ColumnPosition objects
	    intersectionSet.retainAll(idea.getColumnPositions());
	    int intersectionSetSize = intersectionSet.size();

	    // compute overlap of Region output with Ideas in Memory
	    float attention = ((float) intersectionSetSize)
		    / ((float) idea.getColumnPositions().size());
	    float attentionPercentage = attention * 100;

	    idea.setAttentionPercentage(attentionPercentage);
	}
    }

    public Memory getMemory() {
	return this.memory;
    }

    public String getCurrentIdeaNameClassification() {
	float greatestAttentionPercentage = 0;
	Idea ideaWithGreatestAttentionPercentage = null;

	if (this.memory.getIdeas().size() < 1) {
	    throw new IllegalStateException(
		    "memory within MemoryClassifier class "
			    + "getCurrentIdeaNameClassification method must have >= 1 "
			    + "Idea to work");
	}
	for (Idea idea : this.memory.getIdeas()) {
	    if (idea.getAttentionPercentage() > greatestAttentionPercentage) {
		ideaWithGreatestAttentionPercentage = idea;
		greatestAttentionPercentage = idea.getAttentionPercentage();
	    }
	}
	return ideaWithGreatestAttentionPercentage.getName();
    }

    @Override
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();
	stringBuilder.append("\n=============================");
	stringBuilder.append("\n------Neocortex Activity-----");
	for (Idea idea : this.memory.getIdeas()) {
	    stringBuilder.append("\n" + idea.getName() + ": "
		    + idea.getAttentionPercentage() + "%");
	}
	stringBuilder.append("\nRegion ouput Column number: ");
	stringBuilder.append(this.regionOutput.size());
	stringBuilder.append("\n=============================");
	String brainActivityInformation = stringBuilder.toString();
	return brainActivityInformation;
    }
}
