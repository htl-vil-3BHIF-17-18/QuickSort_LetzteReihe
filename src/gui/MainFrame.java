package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bll.SortHelper;

public class MainFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = -8026416994513756565L;

	private JButton sortButton;
	private JButton exitButton;
	private JPanel topPanel;
	private DrawingPanel graphicPanel;
	private ArrayList<Integer> a;

	public MainFrame() {
		initializeControls();
		a = new ArrayList<Integer>();
		for (int i = 1; i <= 60; i++) {
			a.add(i);
		}
		Collections.shuffle(a);
		graphicPanel.drawArray(a, -1, -1, -1);
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
		sortButton = new JButton("Sort");
		sortButton.addActionListener(this);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
		topPanel.add(sortButton);
		topPanel.add(exitButton);
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
		}
	}

	public void drawArray(ArrayList<Integer> a, int index1, int index2, int pivot) {
		graphicPanel.drawArray(a, index1, index2, pivot);
	}

}
