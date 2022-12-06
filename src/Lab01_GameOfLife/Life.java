package Lab01_GameOfLife;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

/*
    this is the Controller component
 */

class Life extends JFrame implements ActionListener, ItemListener {
	private static final long serialVersionUID = 1L;
	static final int SIZE = 60;
	static boolean randomColor = false;
	static boolean paused = false;
	static boolean stopped = true;
	private LifeView view;
	private String file;
	private LifeModel model;
	private JButton runButton, pauseButton, resumeButton, resetButton, randomColorButton;
	private JComboBox<String> fileChooser;

	/** construct a grid using the info in text file */
	private Life() throws IOException {
		super("Conway's Life");
		file = "random";
		// build the buttons
		JPanel controlPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(6, 1, 10, 5));
		buttonPanel.setPreferredSize(new Dimension(100, 200));
		controlPanel.setPreferredSize(new Dimension(125, 640));

		runButton = new JButton("Run");
		runButton.addActionListener(this);
		runButton.setEnabled(true);
		runButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(runButton);

		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(this);
		pauseButton.setEnabled(false);
		pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(pauseButton);

		resumeButton = new JButton("Resume");
		resumeButton.addActionListener(this);
		resumeButton.setEnabled(false);
		runButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(resumeButton);

		fileChooser = new JComboBox<>(getOptions());
		fileChooser.addItemListener(this);
		buttonPanel.add(fileChooser);

		resetButton = new JButton("Reset");
		resetButton.addActionListener(this);
		resetButton.setEnabled(false);
		resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(resetButton);

		randomColorButton = new JButton("Color");
		randomColorButton.addActionListener(this);
		randomColorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonPanel.add(randomColorButton);

		// build the view
		view = new LifeView();
		view.setBackground(Color.white);

		// put buttons, view together
		controlPanel.add(buttonPanel);
		Container c = getContentPane();
		c.add(controlPanel, BorderLayout.EAST);
		c.add(view, BorderLayout.CENTER);

		// build the model
		model = new LifeModel(view, file);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        this.setSize(640, 580);
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		if ( b == runButton ) {
			model.run();
			runButton.setEnabled(false);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
			resetButton.setEnabled(true);
			paused = false;
			stopped = false;
		}
		else if ( b == pauseButton ) {
			model.pause();
			runButton.setEnabled(false);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(true);
			paused = true;
			stopped = false;
		}
		else if ( b == resumeButton ) {
			model.resume();
			runButton.setEnabled(false);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
			paused = false;
			stopped = false;
		}
		else if( b == resetButton ) {
			try {
				model.reset((String) fileChooser.getSelectedItem());
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
			runButton.setEnabled(true);
			resumeButton.setEnabled(false);
			pauseButton.setEnabled(false);
			resetButton.setEnabled(false);
			paused = false;
			stopped = true;
		}
		else if (b == randomColorButton ) {
			Life.randomColor = !Life.randomColor;
			if(paused || stopped) model.colorUpdate();
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == fileChooser && stopped) {
			try {
				model.reset((String) fileChooser.getSelectedItem());
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void start() {
        this.setVisible(true);
    }

	private String[] getOptions() {
		ArrayList<String> options = new ArrayList<>();
		options.add("random");
		File folder = new File("src/Lab01_GameOfLife");
		for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
			if(!fileEntry.isDirectory() && fileEntry.getName().endsWith(".lif")) {
				options.add(fileEntry.getName());
			}
		}

		return options.toArray(String[]::new);
	}


	public static void main(String[] args) throws IOException {
		Life conway = new Life();
		conway.start();
	}
}