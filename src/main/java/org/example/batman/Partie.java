package org.example.batman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Partie {
    private Plateau plateau;
    private Batman batman;
    private List<Ennemi> ennemis;

    public Partie(int taillePlateau) {
        plateau = new Plateau(taillePlateau);
        ennemis = new ArrayList<>();
        genererEnnemis(3);
        placerBatman();
        int nbDeplacementsEstimes = estimerNombreDeplacements();
        System.out.println("Nombre de déplacements estimés pour finir la partie: " + nbDeplacementsEstimes);

    }

    private int estimerNombreDeplacements() {
        int totalDeplacements = 0;
        Batman positionTemporaire = new Batman(batman.getX(), batman.getY());
        List<Ennemi> ennemisRestants = new ArrayList<>(ennemis);

        while (!ennemisRestants.isEmpty()) {
            Ennemi ennemiProche = trouverEnnemiLePlusProcheDepuis(positionTemporaire, ennemisRestants);
            totalDeplacements += distanceEntre(positionTemporaire, ennemiProche);

            positionTemporaire.setX(ennemiProche.getX());
            positionTemporaire.setY(ennemiProche.getY());

            ennemisRestants.remove(ennemiProche);
        }

        return totalDeplacements;
    }

    private Ennemi trouverEnnemiLePlusProcheDepuis(Batman position, List<Ennemi> ennemisListe) {
        Ennemi ennemiProche = null;
        int distanceMin = Integer.MAX_VALUE;

        for (Ennemi ennemi : ennemisListe) {
            int distance = distanceEntre(position, ennemi);
            if (distance < distanceMin) {
                distanceMin = distance;
                ennemiProche = ennemi;
            }
        }
        return ennemiProche;
    }

    private void genererEnnemis(int nombreEnnemis) {
        Random random = new Random();
        int[][] plateauArray = plateau.getPlateau();

        for(int i = 0; i < nombreEnnemis; i++) {
            int x, y;
            do {
                x = random.nextInt(plateauArray.length);
                y = random.nextInt(plateauArray[0].length);
            } while(plateauArray[x][y] != 0);

            plateauArray[x][y] = 1;
            ennemis.add(new Ennemi(x, y));
        }
    }

    private void placerBatman() {
        Random random = new Random();
        int[][] plateauArray = plateau.getPlateau();
        int x, y;
        do {
            x = random.nextInt(plateauArray.length);
            y = random.nextInt(plateauArray[0].length);
        } while(plateauArray[x][y] != 0);

        batman = new Batman(x, y);
    }
    public void deplacerBatmanAutomatiquement() {

    Ennemi ennemiProche = trouverEnnemiLePlusProche();

    int deltaX = batman.getX() - ennemiProche.getX();
    int deltaY = batman.getY() - ennemiProche.getY();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
        if (deltaX > 0) {
            batman.deplacementHaut();
        } else {
            batman.deplacementBas(plateau.getPlateau().length);
        }
    } else {
        if (deltaY > 0) {
            batman.deplacementGauche();
        } else {
            batman.deplacementDroit(plateau.getPlateau()[0].length);
        }
    }

    Iterator<Ennemi> iterator = ennemis.iterator();
        while (iterator.hasNext()) {
        Ennemi ennemi = iterator.next();
        if (ennemi.getX() == batman.getX() && ennemi.getY() == batman.getY()) {
            iterator.remove();
            plateau.getPlateau()[ennemi.getX()][ennemi.getY()] = 0;
            System.out.println("Ennemi tué !");
        }
    }
}

    private Ennemi trouverEnnemiLePlusProche() {
        Ennemi ennemiProche = null;
        int distanceMin = Integer.MAX_VALUE;

        for (Ennemi ennemi : ennemis) {
            int distance = distanceEntre(batman, ennemi);
            if (distance < distanceMin) {
                distanceMin = distance;
                ennemiProche = ennemi;
            }
        }
        return ennemiProche;
    }

    private int distanceEntre(Batman batman, Ennemi ennemi) {
        return Math.abs(batman.getX() - ennemi.getX()) + Math.abs(batman.getY() - ennemi.getY());
    }


    public void jouer() {
        plateau.afficher(batman);
    }

    public boolean estTerminee() {
        return ennemis.isEmpty();
    }
}