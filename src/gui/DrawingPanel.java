package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

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

	public void drawArray(ArrayList<Integer> array, HashMap<Integer, Color> specialColors) {
		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		int height = this.getHeight();
		int width = this.getWidth();
		int widthOfBar = (int) Math.floor(width / array.size());
		int widthOfChart = widthOfBar * array.size();
		for (int i = 0; i < array.size(); i++) {
			int heightOfBar = (int) ((float) (height - margin) / (float) getMaxValue(array) * array.get(i));
			g.setColor(getRainbow(360 * array.get(i) / getMaxValue(array)));
			// g.setColor(new Color(0, checkColor((int) ((255 / (float)
			// array.size()) * array.get(i))), 0));
			if (specialColors.containsKey(i)) {
				g.setColor(specialColors.get(i));
			}
			g.fillRect(i * widthOfBar, bi.getHeight() - heightOfBar - margin, widthOfBar, heightOfBar);
		}
		f.getGraphics().drawImage(bi, 0, margin, (int) (bi.getWidth() * (bi.getWidth() / (float) widthOfChart)),
				bi.getHeight(), null);
	}

	public void drawArray(ArrayList<Integer> array) {
		drawArray(array, new HashMap<Integer, Color>());
	}

	private int getMaxValue(ArrayList<Integer> array) {
		int max = 0;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) > max)
				max = array.get(i);
		}
		return max;
	}

	private Color getRainbow(int hue) {
		int r = 0, g = 0, b = 0;
		double x = 255 * (1 - Math.abs((hue / 60f % 2 - 1)));
		if (hue >= 0 && hue < 60) {
			r = 255;
			g = (int) x;
			b = 0;
		} else if (hue >= 60 && hue < 120) {
			r = (int) x;
			g = 255;
			b = 0;
		} else if (hue >= 120 && hue < 180) {
			r = 0;
			g = 255;
			b = (int) x;
		} else if (hue >= 180 && hue < 240) {
			r = 0;
			g = (int) x;
			b = 255;
		} else if (hue >= 240 && hue < 300) {
			r = (int) x;
			g = 0;
			b = 255;
		} else if (hue >= 300 && hue < 360) {
			r = 255;
			g = 0;
			b = (int) x;
		}
		return new Color(r, g, b);
	}

}
