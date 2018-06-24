package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bll.SortHelper;
import bll.SortHelper.SORT_TYPE;

public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -8026416994513756565L;

	private JButton sortButton;
	private JButton shuffleButton;
	private JButton exitButton;
	private JComboBox<String> comboBoxSortingType;
	private JTextField inputArrayAmount;
	private JPanel topPanel;
	private DrawingPanel graphicPanel;
	private ArrayList<Integer> a;
	private JLabel labelSwapCounter;
	private int swapCounter;

	private SortHelper sh;

	private Runnable sortingRunnable;
	private Thread threadSorting;

	public MainFrame() {
		sh = new SortHelper(this);
		initializeControls();
	}

	private void initializeControls() {
		this.setResizable(false);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setUndecorated(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		this.setLayout(new BorderLayout());

		labelSwapCounter = new JLabel("Balken getauscht : " + swapCounter);
		comboBoxSortingType = new JComboBox<String>(sh.getSortTypes());
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 7));
		inputArrayAmount = new JTextField();
		inputArrayAmount.setBounds(140, 70, 200, 30);
		shuffleButton = new JButton("Shuffle Array");
		shuffleButton.addActionListener(this);
		sortButton = new JButton("Sort");
		sortButton.addActionListener(this);
		sortButton.setEnabled(false);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		topPanel.add(new JLabel(""));
		topPanel.add(sortButton);
		topPanel.add(comboBoxSortingType);
		topPanel.add(shuffleButton);
		topPanel.add(inputArrayAmount);
		topPanel.add(exitButton);
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		topPanel.add(labelSwapCounter);
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		topPanel.add(new JLabel(""));
		this.add(topPanel, BorderLayout.PAGE_START);
		graphicPanel = new DrawingPanel(this);
		this.add(graphicPanel, BorderLayout.CENTER);
		this.pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sortButton) {
			swapCounter = 0;
			sh.setSelectedSort(SORT_TYPE.valueOf((String) comboBoxSortingType.getSelectedItem()));
			sortingRunnable = new Runnable() {
				public void run() {
					sh.sort(a);
					try {
						threadSorting.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			threadSorting = new Thread(sortingRunnable);
			threadSorting.start();
			sortButton.setEnabled(false);
			shuffleButton.setEnabled(false);
			swapCounter = -1;
			incrementSwapCounter();
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		} else if (e.getSource() == shuffleButton) {
			// sodass ein balken maximal 1 pixel breit ist
			int maxBalken = Toolkit.getDefaultToolkit().getScreenSize().width;
			if (Integer.parseInt(inputArrayAmount.getText()) < 0
					|| Integer.parseInt(inputArrayAmount.getText()) > maxBalken) {
				JOptionPane.showMessageDialog(null, "Die Anzahl der Balken ist falsch (0-" + maxBalken + ")");
				return;
			}
			a = new ArrayList<Integer>();
			for (int i = 1; i <= Integer.parseInt(inputArrayAmount.getText()); i++) {
				a.add(i);
			}
			Collections.shuffle(a);
			drawArray(a);
			sortButton.setEnabled(true);
		}
	}

	public void drawArray(ArrayList<Integer> a, HashMap<Integer, Color> specialColors) {
		graphicPanel.drawArray(a, specialColors);
	}

	public void drawArray(ArrayList<Integer> a) {
		drawArray(a, new HashMap<Integer, Color>());
	}

	public void incrementSwapCounter() {
		swapCounter++;
		labelSwapCounter.setText("Balken getauscht : " + swapCounter);
		this.revalidate();
	}

	public void enableShuffleBtn() {
		shuffleButton.setEnabled(true);
	}

}
