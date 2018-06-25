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
	private int imageDrawingHeight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - margin);

	public DrawingPanel(MainFrame f) {
		this.f = f;
		this.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
	}

	public void drawArray(ArrayList<Integer> array, HashMap<Integer, Color> specialColors) {
		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		for (int i = 0; i < array.size(); i++) {
			g.setColor(getRainbow(array, i));
			if (specialColors.containsKey(i)) {
				g.setColor(specialColors.get(i));
			}
			g.fillRect(i, bi.getHeight() - array.get(i), 1, array.get(i));
		}
		f.getGraphics().drawImage(bi, 0, margin, this.getWidth(), imageDrawingHeight, null);
	}

	public void drawArray(ArrayList<Integer> array) {
		drawArray(array, new HashMap<Integer, Color>());
	}

	public void drawBar(ArrayList<Integer> array, int index) {
		BufferedImage tempBi = new BufferedImage(array.size(), getMaxValue(array), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) tempBi.getGraphics();
		g.setColor(Color.white);
		g.fillRect(index, 0, 1, tempBi.getHeight());
		g.setColor(getRainbow(array, index));
		g.fillRect(index, tempBi.getHeight() - array.get(index), 1, array.get(index));
		f.getGraphics().drawImage(tempBi, 0, margin, this.getWidth(), imageDrawingHeight, null);
	}

	public void resetImageWidth(ArrayList<Integer> array) {
		if (bi == null || array.size() != bi.getWidth())
			bi = new BufferedImage(array.size(), getMaxValue(array), BufferedImage.TYPE_INT_ARGB);
	}

	private int getMaxValue(ArrayList<Integer> array) {
		int max = 0;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) > max)
				max = array.get(i);
		}
		return max;
	}

	private Color getRainbow(ArrayList<Integer> a, int index) {
		int hue = 359 * a.get(index) / getMaxValue(a);
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
