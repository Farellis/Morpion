import java.util.Random;

public class Application implements SpecifApplication {
	public int[][] plateau;
	public int[] points;
	public int joueurCourant, coups, evt;
	public IA ia;

	public Application() {
		plateau = new int[3][3];
		joueurCourant = 0;
		coups = 0;
		ia = new IA(this);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				plateau[i][j] = 0;
			}
		}
		points = new int[2];
		evt = 0;
	}

	public void tirerJoueur() {
		evt++;
		Random rand = new Random();
		joueurCourant = rand.nextInt(2) + 1;
	}

	@Override
	public int getJoueur() {
		// TODO Auto-generated method stub
		return joueurCourant;
	}

	@Override
	public int[][] getPlateau() {
		// TODO Auto-generated method stub
		return plateau;
	}

	@Override
	public int vainqueur() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			if (plateau[i][0] == plateau[i][1]
					&& plateau[i][1] == plateau[i][2] && plateau[i][1] != 0) {
				return plateau[i][1];
			}
		}
		for (int i = 0; i < 3; i++) {
			if (plateau[0][i] == plateau[1][i]
					&& plateau[1][i] == plateau[2][i] && plateau[1][i] != 0) {
				return plateau[1][i];
			}
		}
		if ((plateau[0][0] == plateau[1][1] && plateau[1][1] == plateau[2][2])
				|| (plateau[0][2] == plateau[1][1] && plateau[1][1] == plateau[2][0])
				&& plateau[1][1] != 0) {
			return plateau[1][1];
		}
		return 0;
	}

	@Override
	public int partieFinie() {
		// TODO Auto-generated method stub
		int vainqueur = vainqueur();
		if (coups < 9 && vainqueur == 0)
			return -1;
		return vainqueur();
	}

	@Override
	public boolean validerCoup(int l, int c) {
		// TODO Auto-generated method stub
		return (l >= 0 && l < 3 && c >= 0 && c < 3 && plateau[l][c] == 0);

	}

	public boolean plateauVide() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (plateau[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}


	public boolean centreLibre() {
		return plateau[1][1] == 0;
	}

	@Override
	public int jouerCoup(int l, int c) {
		// TODO Auto-generated method stub
		if (!validerCoup(l, c))
			return -2;
		plateau[l][c] = joueurCourant;
		coups++;
		return partieFinie();
	}
	
	public IA getIA() {
		return ia;
	}

	public void relancer() {
		plateau = new int[3][3];
		joueurCourant = 0;
		coups = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				plateau[i][j] = 0;
			}
		}
		evt = 0;
	}
	
	public void reinitialiser() {
		plateau = new int[3][3];
		joueurCourant = 0;
		coups = 0;
		points = new int[2];
		ia.reinitialiser();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				plateau[i][j] = 0;
			}
		}
		evt = 0;
	}

	public int[] getPoints() {
		return points;
	}

}
