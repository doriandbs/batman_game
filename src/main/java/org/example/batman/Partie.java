package org.example.batman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public void deplacerBatman(String direction) {
        switch (direction) {
            case "haut": batman.deplacementHaut(); break;
            case "bas": batman.deplacementBas(plateau.getPlateau().length); break;
            case "gauche": batman.deplacementGauche(); break;
            case "droite": batman.deplacementDroit(plateau.getPlateau()[0].length); break;
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

    public void afficherVisionBatman() {
        int[][] plateauArray = plateau.getPlateau();
        int x = batman.getX();
        int y = batman.getY();

        System.out.println("Vision de Batman : ");
        for (int i = 1; i <= 3; i++) {
            int visionX = x;
            int visionY = y + i;

            if (visionY < plateauArray[0].length) {
                String caseContent = plateauArray[visionX][visionY] == 1 ? "E" : "-";
                System.out.println("Case " + i + ": " + caseContent);
            } else {
                System.out.println("Case " + i + ": hors du plateau");
            }
        }
    }
    public void jouer() {
        plateau.afficher(batman);
    }

}