public class Land{
	
	float[][] sunlight;
	float[][] ogSunlight;

	int x;
	int y;

	static float shadefraction = 0.1f; // only this fraction of light is transmitted by a tree

	Land(int dx, int dy) {
		sunlight = new float[dy][dx];
		ogSunlight = new float[dy][dx];
		x = dx;
		y = dy;

	}

	Land(float[][] sun, float[][] og, int x, int y)
	{
		sunlight = sun;
		ogSunlight = og;
		this.x = x;
		this.y = y;
	}

	int getDimX() {
		int x = sunlight[0].length;
		return x;
	}
	
	int getDimY() {
		int y = sunlight.length;
		return y;
	}

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

	Land cloneLand(){
		float[][] sun = this.cloneSunlight();
		float[][] og = this.cloneSunlight();
		Land result = new Land(sun, og, x, y);
		return result;

	}
	
	// Reset the shaded landscape to the same as the initial sun exposed landscape
	// Needs to be done after each growth pass of the simulator
	void resetShade() {
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				sunlight[i][j] = ogSunlight[i][j];
			}
		}
	}
	
	float getFull(int x, int y) {
		return ogSunlight[y][x];
	}
	
	void setFull(int x, int y, float val) {
		sunlight[y][x] = val;
		ogSunlight[y][x] = val;
	}
	
	float getShade(int x, int y) {

		return sunlight[y][x];
	}
	
	void setShade(int x, int y, float val){
		sunlight[y][x] = shadefraction * sunlight[y][x];
	}
	
	// reduce the 
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