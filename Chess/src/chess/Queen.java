/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.lang.Math;
/**
 *
 * @author PCdePret_2
 */
public class Queen extends Piece{
    
    /**
     * Constructeur
     * @param colourPiece
     * @param typePiece 
     */
    Queen(boolean colourPiece, int typePiece) {
        super(colourPiece, typePiece);
    }
    
    /**
     * Test si la dame a le droit de se déplacer de sa position actuelle à une nouvelle position à tester
     * @param plate : plateau sur lequel sont les pièces
     * @param testedPosition : la position sur laquelle on souhaite déplacer la dame
     * @return true si c'est un mouvement autorisé par la dame
     */
    boolean canMoveTo(Plate plate, int [] testedPosition){
        int [] actualPosition = this.getPosition(plate);
        if (actualPosition[0] == testedPosition[0] && actualPosition[1] == testedPosition[1]){
            return false;
        }
        if (actualPosition[0] == testedPosition[0] 
                || actualPosition[1] == testedPosition[1]
                || Math.abs(actualPosition[0] - testedPosition[0]) == Math.abs(actualPosition[1] - testedPosition[1])){
            return true;
        }
        return false;        
    }
    
    
}
