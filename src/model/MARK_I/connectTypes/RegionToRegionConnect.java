package model.MARK_I.connectTypes;

import model.MARK_I.Region;

/**
 * @author Quinn Liu (quinnliu@vt.edu)
 * @version June 7, 2013
 */
public interface RegionToRegionConnect {
    public abstract void connect(Region childRegion, Region parentRegion,
	    int numberOfColumnsToOverlapAlongXAxisOfRegion,
	    int numberOfColumnsToOverlapAlongYAxisOfRegion);
}
