package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainFrame f;
	private BufferedImage bi;
	private int margin = 75;

	public DrawingPanel(MainFrame f) {
		this.f = f;
		this.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
		bi = new BufferedImage((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
	}

	public void drawArray(ArrayList<Integer> array, int switch1, int switch2, int pivot) {
		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		g.setStroke(new BasicStroke(1));
		int height = this.getHeight();
		int width = this.getWidth();
		int widthOfBar = (int) Math.floor(width / array.size());
		for (int i = 0; i < array.size(); i++) {
			int heightOfBar = (height - margin) / getMaxValue(array) * array.get(i);
			g.setColor(new Color(0, checkColor((int) ((255/(float)array.size())*array.get(i))), 0));
			if (i == switch1 || i == switch2)
				g.setColor(Color.red);
			if (array.get(i) == pivot)
				g.setColor(Color.blue);
			g.fillRect(i * widthOfBar, bi.getHeight() - heightOfBar - margin, widthOfBar, heightOfBar);
		}
		f.getGraphics().drawImage(bi, 0, margin, bi.getWidth(), bi.getHeight(), null);
	}

	private int checkColor(int i) {
		if(i > 255)
			i = 255;
		if(i < 0)
			i = 0;
		return i;
	}
	
	private int getMaxValue(ArrayList<Integer> array) {
		int max = 0;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) > max)
				max = array.get(i);
		}
		return max;
	}

}
