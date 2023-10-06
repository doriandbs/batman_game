package org.example.batman;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Partie partie = new Partie(5);
        while(true) {
            partie.jouer();
            System.out.println("Déplacez Batman (haut/bas/gauche/droite) : ");
            String direction = scanner.next();

            partie.deplacerBatman(direction);
            partie.afficherVisionBatman();
        }

    }

}
