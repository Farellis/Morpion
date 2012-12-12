import java.awt.GridLayout;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MonComposant extends JPanel {
	
	Case[][] cases;
	ControleurGraphique c;
    
    public MonComposant(ControleurGraphique c){
    	super();
    	cases = new Case[3][3];
    	this.c = c;

    	GridLayout gridPlateau = new GridLayout(3, 3);
    	this.setLayout(gridPlateau);
    	for(int i = 0; i<3; i++) {
    		for(int j = 0; j<3; j++) {
    			cases[i][j] = new Case(i,j);
    			this.add(cases[i][j]);
    			cases[i][j].addActionListener(this.c);
    		}
    	}
        this.setVisible(true);  
    }
}
