import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurGraphique implements ActionListener {

	Application application;
	private AffichageGraphique affichageGraphique;

	public ControleurGraphique(Application a, AffichageGraphique ag) {
		application = a;
		affichageGraphique = ag;
	}

	public void IA_jouerCoup() {
		if (application.getIA().jouerIA()) {
			int IA_x = application.getIA().IA_getLigne();
			int IA_y = application.getIA().IA_getColonne();
			affichageGraphique.plateauJeu.cases[IA_x][IA_y].doClick();
		}
	}

	public void demarrerPartie() {
		application.tirerJoueur();
		if (application.getIA().isActivate() && application.getJoueur() == 2) {
			IA_jouerCoup();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == affichageGraphique.jouerplayer) {
			affichageGraphique.afficherPlateau();
			application.reinitialiser();
			application.getIA().desactivate();
			affichageGraphique.reinitialiser();
			demarrerPartie();
		} else if (e.getSource() == affichageGraphique.jouercpu) {
			affichageGraphique.afficherPlateau();
			application.reinitialiser();
			application.getIA().activate();
			affichageGraphique.reinitialiser();
			demarrerPartie();
		} else if (e.getSource() == affichageGraphique.menu) {
			affichageGraphique.afficherMenu();
		} else if (e.getSource() == affichageGraphique.rejouerjeu) {
			affichageGraphique.afficherPlateau();
			application.relancer();
			affichageGraphique.reinitialiser();
			demarrerPartie();
		} else {
			Case caseSelect = (Case) e.getSource();
			int x = caseSelect.getLigne();
			int y = caseSelect.getColonne();
			int res = application.jouerCoup(x, y);
			affichageGraphique.plateauJeu.cases[x][y].setEnabled(false);

			affichageGraphique.mettreAJourVue();

			if (application.joueurCourant == 1) {
				application.joueurCourant = 2;
			} else {
				application.joueurCourant = 1;
			}
			if (res == 0) {
				affichageGraphique.afficherResultat(0);
			} else if (res > 0) {
				application.points[res - 1] = application.points[res - 1] + 1;
				affichageGraphique.mettreAJourScore();
				affichageGraphique.afficherCombinaisonGagnante();
				affichageGraphique.afficherResultat(res);

				// Si gagnant on bloque tous les boutons
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						affichageGraphique.plateauJeu.cases[i][j]
								.setEnabled(false);

					}
				}
			} else {
				if (application.getIA().isActivate()
						&& application.getJoueur() == 2) {
					IA_jouerCoup();
				}
			}
		}
	}
}
