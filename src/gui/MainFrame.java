package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bll.SortHelper;

public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -8026416994513756565L;

	private JButton sortButton;
	private JButton shuffleButton;
	private JButton exitButton;
	private JTextField inputArrayAmount;
	private JPanel topPanel;
	private DrawingPanel graphicPanel;
	private ArrayList<Integer> a;

	public MainFrame() {
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

		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 6));
		inputArrayAmount = new JTextField();
		inputArrayAmount.setBounds(140, 70, 200,30);
		shuffleButton = new JButton("Shuffle Array");
		shuffleButton.addActionListener(this);
		sortButton = new JButton("Sort");
		sortButton.addActionListener(this);
		sortButton.setEnabled(false);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		topPanel.add(new JLabel(""));
		topPanel.add(sortButton);
		topPanel.add(shuffleButton);
		topPanel.add(inputArrayAmount);
		topPanel.add(exitButton);
		topPanel.add(new JLabel(""));
		this.add(topPanel, BorderLayout.PAGE_START);
		graphicPanel = new DrawingPanel(this);
		this.add(graphicPanel, BorderLayout.CENTER);
		this.pack();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sortButton) {
			SortHelper h = SortHelper.getInstance(this);
			h.quicksort(a, 0, a.size() - 1);
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		}else if(e.getSource() == shuffleButton) {
			a = new ArrayList<Integer>();
			for (int i = 1; i <= Integer.parseInt(inputArrayAmount.getText()); i++) {
				a.add(i);
			}
			Collections.shuffle(a);
			drawArray(a, -1, -1, -1);
			sortButton.setEnabled(true);
		}
	}

	public void drawArray(ArrayList<Integer> a, int index1, int index2, int pivot) {
		graphicPanel.drawArray(a, index1, index2, pivot);
	}

}
