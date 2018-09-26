
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class TreeGrow implements ActionListener {
	long startTime = 0;
	int frameX;
	int frameY;
	ForestPanel fp;

	JButton run = new JButton("Run");
	JButton stop = new JButton("Stop");
	JButton reset = new JButton("Reset");
	JButton end = new JButton("End");
	JLabel year = new JLabel("0");

	public TreeGrow(){}
	// start timer
	private void tick(){
		startTime = System.currentTimeMillis();
	}
	
	// stop timer, return time elapsed in seconds
	private float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	
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
	
		
	public static void main(String[] args) {
		TreeGrow t = new TreeGrow();
		SunData sundata = new SunData();

		String input;
		// check that number of command line arguments is correct
		if(args.length != 1)
		{
			//System.out.println("Incorrect number of command line arguments. Should have form: java treeGrow.java intputfilename");
			//System.exit(0);


			//Scanner kin = new Scanner(System.in);
			//input = kin.next();

			input = "sample_input.txt";
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


	}

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

	public void runButton(){}
	public void stopButton(){}
	public void resetButton(){}
}