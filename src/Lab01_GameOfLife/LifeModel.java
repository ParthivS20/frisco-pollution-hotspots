package Lab01_GameOfLife;

import Utils.MyFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener {

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = Life.SIZE;
	private LifeCell[][] grid;
	
	private LifeView myView;
	private Timer timer;

	/** Construct a new model using a particular file */
	LifeModel(LifeView view, String fileName) throws IOException {
		grid = setGrid(fileName);
		myView = view;
		myView.updateView(grid);
	}

	/** pause the simulation (the pause button in the GUI */
	void pause() {
		if(!Life.paused && !Life.stopped) timer.stop();
	}
	
	/** resume the simulation (the pause button in the GUI */
	void resume() {
		timer.restart();
	}
	
	/** run the simulation (the pause button in the GUI */
	void run() {
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	void reset(String fileName) throws FileNotFoundException {
		pause();
		grid = setGrid(fileName);
		myView.updateView(grid);
	}

	void colorUpdate() {
		myView.updateView(grid);
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e) {
		oneGeneration();
		myView.updateView(grid);
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration() {
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[r].length; c++) {
				int n = getOccupiedNeighbors(r, c);
				grid[r][c].setAliveNext(n == 3 || grid[r][c].isAliveNow() && n == 2);
			}
		}

		for (LifeCell[] lifeCells : grid) {
			for (LifeCell lifeCell : lifeCells) {
				lifeCell.setAliveNow(lifeCell.isAliveNext());
			}
		}
	}

	private int getOccupiedNeighbors(int row, int col) {
		int n = 0;

		for(int r = row - 1; r <= row + 1; r++) {
			for(int c = col - 1; c <= col + 1; c++) {
				if(r < 0 || r >= SIZE || c < 0 || c >= SIZE || !grid[r][c].isAliveNow() || r == row && c == col) continue;
				n++;
			}
		}

		return n;
	}

	private LifeCell[][] setGrid(String fileName) throws FileNotFoundException {
		int r, c;
		LifeCell[][] g = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				g[r][c] = new LifeCell();

		//use random population
		if ( fileName == null || fileName.equals("random")) {
			for ( r = 0; r < SIZE; r++ ) {
				for ( c = 0; c < SIZE; c++ ) {
					if (Math.random() > 0.85) //15% chance of a cell starting alive
						g[r][c].setAliveNow(true);
				}
			}
		}
		else {
			Scanner input = new Scanner(new MyFile(fileName, getClass()));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++) {
				r = input.nextInt();
				c = input.nextInt();
				g[r][c].setAliveNow(true);
			}
			input.close();
		}

		return g;
	}
}