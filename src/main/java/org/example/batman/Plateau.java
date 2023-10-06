package org.example.batman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plateau {
    private int[][] plateau;

    public Plateau(int taille) {
        plateau = new int[taille][taille];
    }

    public void afficher(Batman batman) {
        for(int i = 0; i < plateau.length; i++) {
            for(int j = 0; j < plateau[i].length; j++) {
                if(i == batman.getX() && j == batman.getY()) {
                    System.out.print("B ");
                } else if (plateau[i][j] == 1) {
                    System.out.print("J ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }


}
