package org.example.batman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Batman {
    private int x, y;
    public void deplacementHaut() {
        x = x > 0 ? x-1 : x;
    }

    public void deplacementBas(int boardSize) {
        x = x < boardSize-1 ? x+1 : x;
    }

    public void deplacementGauche() {
        y = y > 0 ? y-1 : y;
    }

    public void deplacementDroit(int boardSize) {
        y = y < boardSize-1 ? y+1 : y;
    }
}