
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class TreeGrow implements ActionListener {
	/**
	 * Width of the UI
	 */
	int frameX;
	/**
	 * Length of the UI
	 */
	int frameY;
	/**
	 * ForestPanel
	 */
	ForestPanel fp;

	/**
	 * Run button
	 */
	JButton run = new JButton("Run");
	/**
	 * Stop button
	 */
	JButton stop = new JButton("Stop");
	/**
	 * Reset button
	 */
	JButton reset = new JButton("Reset");
	/**
	 * End button
	 */
	JButton end = new JButton("End");
	/**
	 * Year counter
	 */
	static JLabel year = new JLabel("0");
	/**
	 * RecursiveOperation "controller"
	 */
	static RecursiveOperations ro;
	/**
	 * User input
	 */
	static String input;


	/**
	 * Default Constructor
	 */
	public TreeGrow(){}

	/**
	 * Sets up the UI
	 * @param frameX	X-size
	 * @param frameY	Y-Size
	 * @param trees	Trees array to monitor
	 */
	public void setupGUI(int frameX,int frameY,Tree [] trees) {
		Dimension fsize = new Dimension(800, 800);
		// Frame init and dimensions
    	JFrame frame = new JFrame("Photosynthesis"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setPreferredSize(fsize);
    	frame.setSize(800, 800);
    	
      	JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      	g.setPreferredSize(fsize);
 
		fp = new ForestPanel(trees);
		fp.setPreferredSize(new Dimension(frameX,frameY));
		JScrollPane scrollFrame = new JScrollPane(fp);
		fp.setAutoscrolls(true);
		scrollFrame.setPreferredSize(fsize);
	    g.add(scrollFrame);
    	
      	frame.setLocationRelativeTo(null);  // Center window on screen.
      	frame.add(g); //add contents to window
        frame.setContentPane(g);     
        frame.setVisible(true);
        Thread fpt = new Thread(fp);
        fpt.start();

        JFrame buttons = new JFrame("Control Panel");
        buttons.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttons.setSize(400,100);

        JPanel b = new JPanel();


		run.addActionListener(this);


		stop.addActionListener(this);


		reset.addActionListener(this);


		end.addActionListener(this);



		b.setLayout(new FlowLayout());
        b.add(run);
        b.add(stop);
        b.add(reset);
        b.add(end);
        b.add(year);

        buttons.add(b);
        buttons.setVisible(true);

	}


	/**
	 * Main method. Starts up the UI, and begins the simulation
	 * @param args User input.
	 */
	public static void main(String[] args) {
		TreeGrow t = new TreeGrow();
		SunData sundata = new SunData();
		// check that number of command line arguments is correct
		if(args.length != 1)
		{
			//System.out.println("Incorrect number of command line arguments. Should have form: java treeGrow.java intputfilename");
			//System.exit(0);


			Scanner kin = new Scanner(System.in);
			input = kin.next();

			//input = "sample_input.txt";
			//input = "smalltest.txt";
		}
		else
		{
			input = args[0];
		}
				
		// read in forest and landscape information from file supplied as argument
		sundata.readData(input);

		System.out.println("Data loaded");
		
		t.frameX = sundata.sunmap.getDimX();
		t.frameY = sundata.sunmap.getDimY();
		t.setupGUI(t.frameX, t.frameY, sundata.trees);
		
		// create and start simulation loop here as separate thread
		ro = new RecursiveOperations(sundata);
		while (true)
		{
			ro.incrementYear();
			year.setText(ro.currentYear + "");
		}


	}

	/**
	 * Listens for button presses. If a button is pressed, activates it's associated method.
	 * @param e ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == run)
		{
			runButton();
		}
		if (e.getSource() == stop)
		{
			stopButton();
		}
		if (e.getSource() == reset)
		{
			resetButton();
		}
		if (e.getSource() == end)
		{
			System.exit(0);
		}

	}

	/**
	 * Activates if the run button is pressed. Unpauses the simulation.
	 */
	public void runButton(){
		ro.resume();
	}

	/**
	 * Activates if the stop button is pressed. Pauses the simulation.
	 */
	public void stopButton(){
		ro.pause();
	}

	/**
	 * Activates if the reset button is pressed. Resets the simulation by reading in the file again, while setting all Tree extents to 0.4f.
	 */
	public void resetButton(){
		ro.pause();
		SunData s = new SunData();
		s.readDataSapling(input);
		RecursiveOperations rnew = new RecursiveOperations(s);
		ro = rnew;
		fp.changeTrees(s.trees);
	}
}