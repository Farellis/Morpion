import java.util.Random;

public class IA {
	public Application application;
	public int IA_ligne;
	public int IA_colonne;
	public boolean activate;

	public IA(Application a) {
		application = a;
		activate = true;
		IA_ligne = 0;
		IA_colonne = 0;
	}

	public int IA_getLigne() {
		return IA_ligne;
	}

	public int IA_getColonne() {
		return IA_colonne;
	}

	public boolean IA_firstTime() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (application.plateau[i][j] == 2) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean IA_FinishOrPrevent() {
		for (int p = 2; p >= 1; p--) {
			/* Les lignes */
			for (int i = 0; i <= 2; i++) {
				if (application.plateau[i][0] == p
						&& application.plateau[i][1] == p
						&& application.plateau[i][2] == 0) {
					IA_ligne = i;
					IA_colonne = 2;
					return true;
				} else if (application.plateau[i][0] == p
						&& application.plateau[i][2] == p
						&& application.plateau[i][1] == 0) {
					IA_ligne = i;
					IA_colonne = 1;
					return true;
				} else if (application.plateau[i][1] == p
						&& application.plateau[i][2] == p
						&& application.plateau[i][0] == 0) {
					IA_ligne = i;
					IA_colonne = 0;
					return true;
				}
			}
			/* Les colonnes */
			for (int i = 0; i <= 2; i++) {
				if (application.plateau[0][i] == p
						&& application.plateau[1][i] == p
						&& application.plateau[2][i] == 0) {
					IA_ligne = 2;
					IA_colonne = i;
					return true;
				} else if (application.plateau[0][i] == p
						&& application.plateau[2][i] == p
						&& application.plateau[1][i] == 0) {
					IA_ligne = 1;
					IA_colonne = i;
					return true;
				} else if (application.plateau[1][i] == p
						&& application.plateau[2][i] == p
						&& application.plateau[0][i] == 0) {
					IA_ligne = 0;
					IA_colonne = i;
					return true;
				}
			}
			/* Les diagonales */
			if (application.plateau[0][0] == p
					&& application.plateau[1][1] == p
					&& application.plateau[2][2] == 0) {
				IA_ligne = 2;
				IA_colonne = 2;
				return true;
			} else if (application.plateau[0][0] == p
					&& application.plateau[2][2] == p
					&& application.plateau[1][1] == 0) {
				IA_ligne = 1;
				IA_colonne = 1;
				return true;
			} else if (application.plateau[1][1] == p
					&& application.plateau[2][2] == p
					&& application.plateau[0][0] == 0) {
				IA_ligne = 0;
				IA_colonne = 0;
				return true;
			} else if (application.plateau[0][2] == p
					&& application.plateau[1][1] == p
					&& application.plateau[2][0] == 0) {
				IA_ligne = 2;
				IA_colonne = 0;
				return true;
			} else if (application.plateau[0][2] == p
					&& application.plateau[2][0] == p
					&& application.plateau[1][1] == 0) {
				IA_ligne = 1;
				IA_colonne = 1;
				return true;
			} else if (application.plateau[1][1] == p
					&& application.plateau[2][0] == p
					&& application.plateau[0][2] == 0) {
				IA_ligne = 0;
				IA_colonne = 2;
				return true;
			}
		}
		return false;
	}

	public boolean IA_add() {
		for (int i = 0; i <= 2; i++) {
			if ((application.plateau[i][0] == 2
					&& application.plateau[i][1] == 0 && application.plateau[i][2] == 0)
					|| (application.plateau[i][0] == 0
							&& application.plateau[i][1] == 2 && application.plateau[i][2] == 0)
					|| ((application.plateau[i][0] == 0
							&& application.plateau[i][1] == 0 && application.plateau[i][2] == 2))) {
				Random rand = new Random();
				int c = rand.nextInt(3);
				while (application.plateau[i][c] != 0) {
					c = rand.nextInt(3);
				}
				IA_ligne = i;
				IA_colonne = c;
				return true;
			}
		}
		for (int i = 0; i <= 2; i++) {
			if ((application.plateau[0][i] == 2
					&& application.plateau[1][i] == 0 && application.plateau[2][i] == 0)
					|| (application.plateau[0][i] == 0
							&& application.plateau[1][i] == 2 && application.plateau[2][i] == 0)
					|| ((application.plateau[0][i] == 0
							&& application.plateau[1][i] == 0 && application.plateau[2][i] == 2))) {
				Random rand = new Random();
				int l = rand.nextInt(3);
				while (application.plateau[l][i] != 0) {
					l = rand.nextInt(3);
				}
				IA_ligne = l;
				IA_colonne = i;

				return true;
			}
		}
		if ((application.plateau[0][0] == 2 && application.plateau[1][1] == 0 && application.plateau[2][2] == 0)
				|| (application.plateau[0][0] == 0
						&& application.plateau[1][1] == 2 && application.plateau[2][2] == 0)
				|| ((application.plateau[0][0] == 0
						&& application.plateau[1][1] == 0 && application.plateau[2][2] == 2))) {
			Random rand = new Random();
			int k = rand.nextInt(3);
			while (application.plateau[k][k] != 0) {
				k = rand.nextInt(3);
			}
			IA_ligne = k;
			IA_colonne = k;
			return true;
		}
		if ((application.plateau[0][2] == 2 && application.plateau[1][1] == 0 && application.plateau[2][0] == 0)
				|| (application.plateau[0][2] == 0
						&& application.plateau[1][1] == 2 && application.plateau[2][0] == 0)
				|| ((application.plateau[0][2] == 0
						&& application.plateau[1][1] == 0 && application.plateau[2][0] == 2))) {
			Random rand = new Random();
			int l = rand.nextInt(3);
			int c = 2 - l;
			while (application.plateau[l][c] != 0) {
				l = rand.nextInt(3);
				c = 2 - l;
			}
			IA_ligne = l;
			IA_colonne = c;

			return true;
		}
		return false;
	}

	public boolean IA_randomPlay() {
		boolean play = false;
		while (!play) {
			Random rand = new Random();
			int l = rand.nextInt(3);
			int c = rand.nextInt(3);

			if (application.plateau[l][c] == 0) {
				IA_ligne = l;
				IA_colonne = c;
				play = true;
			}
		}
		return play;
	}

	public boolean isActivate() {
		return activate;
	}

	public boolean jouerIA() {
		if (application.plateauVide()) {
			Random rand = new Random();
			int r = rand.nextInt(10);
			if (r >= 3) {
				IA_ligne = 1;
				IA_colonne = 1;
				return true;
			}
		} else if (IA_firstTime()) {
			if (application.centreLibre()) {
				Random rand = new Random();
				int r = rand.nextInt(10);
				if (r >= 4) {
					IA_ligne = 1;
					IA_colonne = 1;
					return true;
				}
			} else {
				return IA_randomPlay();
			}
		} else if (IA_FinishOrPrevent()) {
			return true;
		} else if (IA_add()) {
			return true;
		}
		return IA_randomPlay();
	}

	public void activate() {
		activate = true;
	}

	public void desactivate() {
		activate = false;
	}

	public void reinitialiser() {
		activate = true;
		IA_ligne = 0;
		IA_colonne = 0;
	}
}
