import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

// Trees define a canopy which covers a square area of the landscape
public class SunData{
	
	Land sunmap; // regular grid with average daily sunlight stored at each grid point
	Tree [] trees;// array of individual tress located on the sunmap

	
	public SunData(Land l, Tree[] t){
		sunmap = l;
		trees = t;
	}

	public SunData(){}


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

	void resetSunlight(){
		sunmap.resetShade();
	}

	SunData cloneSundata(){
		Land sun = sunmap.cloneLand();
		Tree[] t = new Tree[trees.length];
		for (int i = 0; i < trees.length; i++)
		{
			t[i] = trees[i].cloneTree();
		}

		SunData result = new SunData(sun, t);
		return result;
	}

	SunData cloneResetSundata(){
		Land sun = sunmap.cloneLand();
		Tree[] t = new Tree[trees.length];
		for (int i = 0; i < trees.length; i++)
		{
			t[i] = trees[i].cloneTreeSapling();
		}

		SunData result = new SunData(sun, t);
		return result;
	}
	
	void writeData(String fileName){
		 try{ 
			 FileWriter fileWriter = new FileWriter(fileName);
			 PrintWriter printWriter = new PrintWriter(fileWriter);
			 printWriter.printf("%d\n", trees.length);
			 for(int t=0; t < trees.length; t++)
				 printWriter.printf("%d %d %f\n", trees[t].getX(), trees[t].getY(), trees[t].getExt());
			 printWriter.close();
		 }
		 catch (IOException e){
			 System.out.println("Unable to open output file "+fileName);
				e.printStackTrace();
		 }
	}
	
}