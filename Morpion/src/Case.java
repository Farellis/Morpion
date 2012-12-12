import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Case extends JButton {

	private int ligne;
	private int colonne;
	
	public Case(int l, int c) {
		super(" ");
		ligne = l;
		colonne = c;
		this.setEnabled(false);
		Font font = new Font(Font.DIALOG, Font.BOLD, 60);
		this.setFont(font);
	}
	
	public int getLigne() {
		return ligne;
	}
	
	public int getColonne() {
		return colonne;
	}
}
