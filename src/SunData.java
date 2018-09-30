import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class SunData{

	/**
	 * regular grid with average daily sunlight stored at each grid point
	 */
	Land sunmap;
	/**
	 * array of individual tress located on the sunmap
	 */
	Tree [] trees;

	/**
	 * Constructor. Creates a SunData object containing the supplied variables
	 * @param l Land
	 * @param t Forest
	 */
	public SunData(Land l, Tree[] t){
		sunmap = l;
		trees = t;
	}

	/**
	 * Default Constructor
	 */

	public SunData(){}

	/**
	 * Reads in a file, and sets the values of sunmap and trees to it's contents.
	 * @param fileName	The filename
	 */
	void readData(String fileName){
		try{ 
			Scanner sc = new Scanner(new File(fileName));
			
			// load sunmap
			int dimx = sc.nextInt(); 
			int dimy = sc.nextInt();
			sunmap = new Land(dimx,dimy);
			for(int x = 0; x < dimx; x++)
				for(int y = 0; y < dimy; y++) {
					sunmap.setFull(x,y,sc.nextFloat());	
				}
			sunmap.resetShade();
			
			// load forest
			int numt = sc.nextInt();
			trees = new Tree[numt];
			for(int t=0; t < numt; t++)
			{
				int xloc = sc.nextInt();
				int yloc = sc.nextInt();
				float ext = (float) sc.nextInt();
				trees[t] = new Tree(xloc, yloc, ext);
			}
			sc.close(); 
		} 
		catch (IOException e){ 
			System.out.println("Unable to open input file "+fileName);
			e.printStackTrace();
		}
		catch (java.util.InputMismatchException e){ 
			System.out.println("Malformed input file "+fileName);
			e.printStackTrace();
		}
	}

	/**
	 * Reads in a file, and sets the values of sunmap and trees to it's contents. However, all of the Tree extents are set to 0.4f
	 * @param fileName The filename
	 */
	void readDataSapling(String fileName){
		try{
			Scanner sc = new Scanner(new File(fileName));

			// load sunmap
			int dimx = sc.nextInt();
			int dimy = sc.nextInt();
			sunmap = new Land(dimx,dimy);
			for(int x = 0; x < dimx; x++)
				for(int y = 0; y < dimy; y++) {
					sunmap.setFull(x,y,sc.nextFloat());
				}
			sunmap.resetShade();

			// load forest
			int numt = sc.nextInt();
			trees = new Tree[numt];
			for(int t=0; t < numt; t++)
			{
				int xloc = sc.nextInt();
				int yloc = sc.nextInt();
				float ext = (float) sc.nextInt();
				ext = 0.4f;
				trees[t] = new Tree(xloc, yloc, ext);
			}
			sc.close();
		}
		catch (IOException e){
			System.out.println("Unable to open input file "+fileName);
			e.printStackTrace();
		}
		catch (java.util.InputMismatchException e){
			System.out.println("Malformed input file "+fileName);
			e.printStackTrace();
		}
	}

	/**
	 * Sets the sunmap's sunlight to it's original values.
	 */

	void resetSunlight(){
		sunmap.resetShade();
	}

	
}