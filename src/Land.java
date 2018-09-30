public class Land{

	/**
	 * The sunmap, containing the values of sunlight assigned to each block of land
	 */
	float[][] sunlight;
	/**
	 * A copy of the above
	 */
	float[][] ogSunlight;

	/**
	 * The x-size of the land
	 */
	int x;
	/**
	 * The y-size of the land
	 */
	int y;

	/**
	 * The fraction of light let through each tree
	 */
	static float shadefraction = 0.1f;

	/**
	 * Constructor. Initialises an empty Land object with the dimensions provided.
	 * @param dx	X-dimension
	 * @param dy	Y-dimension
	 */
	Land(int dx, int dy) {
		sunlight = new float[dy][dx];
		ogSunlight = new float[dy][dx];
		x = dx;
		y = dy;

	}

	/**
	 * Constructor. Initialises a Land object with the variables provided
	 * @param sun	Sunmap
	 * @param og	Copy of Sunmap
	 * @param x		X-Dimension
	 * @param y		Y-Dimension
	 */

	Land(float[][] sun, float[][] og, int x, int y)
	{
		sunlight = sun;
		ogSunlight = og;
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the X-Dimension of the Land
	 * @return	The X-Dimension of the Land
	 */

	int getDimX() {
		int x = sunlight[0].length;
		return x;
	}
	/**
	 * Returns the Y-Dimension of the Land
	 * @return	The Y-Dimension of the Land
	 */
	
	int getDimY() {
		int y = sunlight.length;
		return y;
	}

	/**
	 * Produces a deep-copy of the sunmap
	 * @return The cloned sunmap
	 */

	float[][] cloneSunlight(){
		float[][] result = new float[sunlight.length][sunlight[0].length];
		for (int i = 0; i < sunlight.length; i++)
		{
			for (int j = 0; i < sunlight[0].length; j++)
			{
				result[i][j] = ogSunlight[i][j];
			}
		}
		return result;
	}

	/**
	 * Produces a deep-copy of the Land object
	 * @return The cloned Land
	 */

	Land cloneLand(){
		float[][] sun = this.cloneSunlight();
		float[][] og = this.cloneSunlight();
		Land result = new Land(sun, og, x, y);
		return result;

	}

	/**
	 * Resets the shade of the sunmap to its original values
	 */
	void resetShade() {
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				sunlight[i][j] = ogSunlight[i][j];
			}
		}
	}

	/**
	 * Returns the original sunlight value at that location
	 * @param x	X-Location
	 * @param y	Y-Location
	 * @return	The sunlight value
	 */
	
	float getFull(int x, int y) {
		return ogSunlight[y][x];
	}

	/**
	 * Sets the original sunlight value at that location
	 * @param x X-Location
	 * @param y	Y-Location
	 * @param val	The Value to set it as
	 */
	
	void setFull(int x, int y, float val) {
		sunlight[y][x] = val;
		ogSunlight[y][x] = val;
	}

	/**
	 * Returns the shaded sunlight value at that location
	 * @param x	X-Location
	 * @param y	Y-Location
	 * @return	The shaded sunlight value
	 */
	
	float getShade(int x, int y) {

		return sunlight[y][x];
	}

	/**
	 * Sets the shaded sunlight value at that location
	 * @param x	X-Location
	 * @param y	Y-Location
	 * @param val	The value to set
	 */
	
	void setShade(int x, int y, float val){
		sunlight[y][x] = shadefraction * sunlight[y][x];
	}

	/**
	 * Shadows the sunmap according to the location of a tree
	 * @param tree	The tree to shadow by
	 */
	void shadow(Tree tree){
		int x = tree.getX();
		int y = tree.getY();
		float extent = tree.getExt();
		int diameter = (int)extent*2;
		for (int i = y; i < (y + diameter); i++)
		{
			for (int j = x; j < (x+diameter); j++)
			{
				setShade(j, i, sunlight[j][i]);
			}
		}

	}
}