package org.example.batman;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Partie partie = new Partie(5);
        while(!partie.estTerminee()) {
            partie.jouer();
            System.out.println("**************************");
            partie.deplacerBatmanAutomatiquement();
        }
        System.out.println("Partie terminée !");

    }

}
