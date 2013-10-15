import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class AffichageGraphique extends JFrame {

	MonComposant plateauJeu;
	JTextPane score;
	ControleurGraphique c;
	JPanel jouer;
	JButton jouerplayer;
	JButton jouercpu;
	JPanel rejouer;
	JButton rejouerjeu;
	JButton menu;

	public AffichageGraphique(Application jeu) {
		super("Jeu du Morpion");
		c = new ControleurGraphique(jeu, this);
		plateauJeu = new MonComposant(c);

		/* Création des Rejouer */
		jouer = new JPanel();
		GridLayout gridJouer = new GridLayout(1, 2);
		jouer.setLayout(gridJouer);
		jouerplayer = new JButton("Player vs Player");
		jouercpu = new JButton("Player vs CPU");
		jouerplayer.setSize(10, 10);
		jouercpu.setSize(10, 10);
		jouerplayer.setEnabled(true);
		jouerplayer.addActionListener(c);
		jouercpu.setEnabled(true);
		jouercpu.addActionListener(c);
		jouer.add(jouerplayer);
		jouer.add(jouercpu);
		jouer.setVisible(true);

		/* Création des Rejouer */
		rejouer = new JPanel();
		GridLayout gridRejouer = new GridLayout(1, 2);
		rejouer.setLayout(gridRejouer);
		rejouerjeu = new JButton("Rejouer");
		rejouerjeu.setSize(10, 10);
		rejouerjeu.setEnabled(true);
		rejouerjeu.addActionListener(c);
		menu = new JButton("Menu");
		menu.setSize(10, 10);
		menu.setEnabled(true);
		menu.addActionListener(c);
		rejouer.add(menu);
		rejouer.add(rejouerjeu);
		rejouer.setVisible(true);

		// Ajout du score
		score = new JTextPane();
		score.setOpaque(true);
		score.setEditable(false);
		StyledDocument doc = score.getStyledDocument();
		MutableAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, 0, center, true);

		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		this.add("South", jouer);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(600, 330);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void afficherResultat(int res) {
		int[] points = c.application.getPoints();
		rejouerjeu.setEnabled(true);
		if(res == 0) {
			score.setText("Match nul !" + " [" + String.valueOf(points[0]) + "] - ["
					+ String.valueOf(points[1]) + "]");
		} else {
			score.setText("Joueur " + res + " gagne la partie !" + " [" + String.valueOf(points[0]) + "] - ["
					+ String.valueOf(points[1]) + "]");
		}
		menu.setEnabled(true);
	}
	
	public void afficherJoueurDebut() {
		score.setText("Joueur "+ c.application.getJoueur() + " commence la partie !");
	}

	public void afficherMenu() {
		this.remove(plateauJeu);
		this.remove(rejouer);
		this.remove(score);
		this.add("South", jouer);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(600, 330);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void afficherPlateau() {
		this.add("North", score);
		rejouerjeu.setEnabled(false);
		menu.setEnabled(false);
		this.add("South", rejouer);
		this.add(plateauJeu);
		this.remove(jouer);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(600, 330);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void mettreAJourJoueurEnCours() {
		score.setText("Au tour du joueur "+ c.application.getJoueur() + " !");
	}


	public void mettreAJourVue() {
		// TODO Auto-generated method stub
		int[][] plateau = c.application.plateau;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				String symbole = " ";
				if (plateau[i][j] == 1) {
					symbole = "X";
				} else if (plateau[i][j] == 2) {
					symbole = "O";
				}
				plateauJeu.cases[i][j].setText(symbole);
			}
		}
	}

	public void afficherCombinaisonGagnante() {
		int[][] plateau = c.application.plateau;
		for (int i = 0; i < 3; i++) {
			if (plateau[i][0] == plateau[i][1]
					&& plateau[i][1] == plateau[i][2] && plateau[i][1] != 0) {
				plateauJeu.cases[i][0].setBackground(Color.GREEN);
				plateauJeu.cases[i][1].setBackground(Color.GREEN);
				plateauJeu.cases[i][2].setBackground(Color.GREEN);
			}
		}
		for (int i = 0; i < 3; i++) {
			if (plateau[0][i] == plateau[1][i]
					&& plateau[1][i] == plateau[2][i] && plateau[1][i] != 0) {
				plateauJeu.cases[0][i].setBackground(Color.GREEN);
				plateauJeu.cases[1][i].setBackground(Color.GREEN);
				plateauJeu.cases[2][i].setBackground(Color.GREEN);
			}
		}
		if ((plateau[0][0] == plateau[1][1] && plateau[1][1] == plateau[2][2])
				&& plateau[1][1] != 0) {
			plateauJeu.cases[0][0].setBackground(Color.GREEN);
			plateauJeu.cases[1][1].setBackground(Color.GREEN);
			plateauJeu.cases[2][2].setBackground(Color.GREEN);
		}

		if ((plateau[0][2] == plateau[1][1] && plateau[1][1] == plateau[2][0])
				&& plateau[1][1] != 0) {
			plateauJeu.cases[0][2].setBackground(Color.GREEN);
			plateauJeu.cases[1][1].setBackground(Color.GREEN);
			plateauJeu.cases[2][0].setBackground(Color.GREEN);
		}

	}

	public ControleurGraphique getControleur() {
		// TODO Auto-generated method stub
		return c;
	}

	public void reinitialiser() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				plateauJeu.cases[i][j].setEnabled(true);
				plateauJeu.cases[i][j].setText(" ");
				plateauJeu.cases[i][j].setBackground(null);
			}
		}
	}
}
