package Lab01_GameOfLife;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LifeCell extends JButton {
    /** the current state (alive or dead) of a particular cell */
	private boolean aliveNow;

	/** the next state / generation (alive or dead) of a particular cell */
    private boolean aliveNext;

    public LifeCell() {
        aliveNow = false;
        aliveNext = false;
    }

    public boolean isAliveNow() { return aliveNow; }

    public void setAliveNow(boolean a) { aliveNow = a; }

    public boolean isAliveNext() { return aliveNext; }

    public void setAliveNext(boolean a) { aliveNext = a; }
}
