
// Trees define a canopy which covers a square area of the landscape
public class Tree{

private
/**
 *  x-coordinate of center of tree canopy
  */
	int xpos;
	/**
	 * y-coorindate of center of tree canopy
	 */
	int ypos;
	/**
	 * extent of canopy out in vertical and horizontal from center
	 */
	float ext;

	/**
	 * divide average sun exposure by this amount to get growth in extent
	 */
	float growfactor = 1000.0f;

	/**
	 * Constructor. Sets the values of xpo, ypos, and ext.
	 * @param x	X-coordinate
	 * @param y	Y-coordinate
	 * @param e	Extent
	 */
	public
	Tree(int x, int y, float e){
		xpos=x; ypos=y; ext=e;
	}


	/**
	 * Returns X-Coordinate
	 * @return X-coordinate
	 */
	int getX() {
		return xpos;
	}
	/**
	 * Returns Y-Coordinate
	 * @return Y-coordinate
	 */
	int getY() {
		return ypos;
	}

	/**
	 * Returns Extent
	 * @return Extent
	 */
	
	float getExt() {
		return ext;
	}


}