import java.awt.*;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.util.*;


public class ForestPanel extends JPanel implements Runnable {
	Tree[] forest;	// trees to render
	List<Integer> rndorder; // permutation of tree indices so that rendering is less structured

	private JButton run = new JButton("Run");
	private JButton stop = new JButton("Stop");
	private JButton reset = new JButton("Reset");
	private JButton end = new JButton("End");
	private JLabel year = new JLabel("0");
	
	ForestPanel(Tree[] trees) {
		forest=trees;
		JPanel newPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10,10,10,10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		newPanel.add(run, constraints);
		constraints.gridx = 1;
		newPanel.add(stop, constraints);
	}
		
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		g.clearRect(0,0,width,height);
		    
		// draw the forest in different canopy passes
		Random rnd = new Random(0); // providing the same seed gives trees consistent colouring

		// start from small trees of [0, 2) extent
		float minh = 0.0f;
		float maxh = 2.0f;
		for(int layer = 0; layer <= 10; layer++) {
			for(int t = 0; t < forest.length; t++){
				int rt = rndorder.get(t); 
				if(forest[rt].getExt() >= minh && forest[rt].getExt() < maxh) { // only render trees in current band
					// draw trees as rectangles centered on getX, getY with random greenish colour
					g.setColor(new Color(rnd.nextInt(100), 150+rnd.nextInt(100), rnd.nextInt(100)));
					g.fillRect(forest[rt].getY() - (int) forest[rt].getExt(), forest[rt].getX() - (int) forest[rt].getExt(),
						   2*(int) forest[rt].getExt()+1,2*(int) forest[rt].getExt()+1);
				}
				// g.setColor(Color.black);
				// g.fillRect(forest[rt].getY(), forest[rt].getX(), 1, 1); // draw the trunk
			}
			minh = maxh;  // next band of trees
			maxh += 2.0f;
		}	
	}

	public void runButton(){}

	public void stopButton(){}

	public void resetButton(){}

	public void endButton(){}

	public void changeYear(){}
	
	public void run() {
			
		// reordering so that trees are rendered in a more random fashion
		rndorder = new ArrayList<Integer>();
		for(int l = 0; l < forest.length; l++)
			rndorder.add(l);
		java.util.Collections.shuffle(rndorder);
		
		// render loop
		while(true) {
			repaint();
			try {
				Thread.sleep(20); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			};
		}
	}

}