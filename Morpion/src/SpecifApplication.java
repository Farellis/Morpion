
public interface SpecifApplication {

	public int getJoueur();
	
	public IA getIA();
	
	public int[][] getPlateau();
	
	public int vainqueur();
	
	public int partieFinie();
	
	public boolean centreLibre();
	
	public boolean validerCoup(int l, int c);
	
	public int jouerCoup(int l, int c);
}
