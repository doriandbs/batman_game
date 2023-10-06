package org.example.batman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    public void jouer() {
        plateau.afficher(batman);
    }

}