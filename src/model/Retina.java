package model;

import model.MARK_I.VisionCell;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Input to Retine: images of different possible formats.
 *
 * Output of Retine: activity of Cells within VisionCells.
 *
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version Dec 28, 2013
 */
public class Retina {
    private VisionCell[][] visionCells;

    public Retina(int numberOfVisionCellsAlongXAxis,
	    int numberOfVisionCellsAlongYAxis) {
	this.visionCells = new VisionCell[numberOfVisionCellsAlongXAxis][numberOfVisionCellsAlongYAxis];

	for (int x = 0; x < this.visionCells.length; x++) {
	    for (int y = 0; y < this.visionCells[0].length; y++) {
		this.visionCells[x][y] = new VisionCell();
	    }
	}
    }

    public VisionCell[][] getVisionCells() {
	return this.visionCells;
    }

    /**
     * Update the state of the Retine with the given .bmp file name.
     *
     * @param BMPFileName
     * @throws IOException
     */
    public void seeBMPImage(String BMPFileName) throws IOException {
	BufferedImage image = ImageIO.read(getClass().getResource(BMPFileName));

	int numberOfRows = this.visionCells.length;
	int numberOfColumns = this.visionCells[0].length;

	for (int currentRow = 0; currentRow < numberOfRows; currentRow++) {
	    for (int currentColumn = 0; currentColumn < numberOfColumns; currentColumn++) {
		int RGBcolor = image.getRGB(currentRow, currentColumn);

		if (RGBcolor == Color.BLACK.getRGB()) {
		    this.visionCells[currentRow][currentColumn]
			    .setActiveState(true);
		} else {
		    this.visionCells[currentRow][currentColumn]
			    .setActiveState(false);
		}
	    }
	}
    }

    /**
     * @param image
     *            Any element in the 2D array with a value of zero will mean the
     *            corresponding VisionCell 2D array element will have
     *            activeState set to false. Any other number sets activeState to
     *            true.
     */
    public void see2DIntArray(int[][] image) {
	for (int yPixel = 0; yPixel < this.visionCells[0].length; yPixel++) {
	    for (int xPixel = 0; xPixel < this.visionCells.length; xPixel++) {
		if (image[xPixel][yPixel] != 0) {
		    this.visionCells[xPixel][yPixel].setActiveState(true);
		} else {
		    this.visionCells[xPixel][yPixel].setActiveState(false);
		}
	    }
	}
    }

    public void seeJPEGImage(String JPEGFileName) {
	// TODO: implement...
    }
}
