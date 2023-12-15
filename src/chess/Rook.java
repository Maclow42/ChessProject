/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author PCdePret_2
 */
public class Rook extends Piece{
    
    /**
     * Constructeur
     * @param colourPiece
     * @param typePiece 
     */
    Rook(boolean colourPiece, int typePiece){
        super(colourPiece, typePiece);
    }
    
    /**
     * Test si la tour a le droit de se déplacer de sa position actuelle à une nouvelle position à tester
     * @param plate : plateau sur lequel sont les pièces
     * @param testedPosition : la position sur laquelle on souhaite déplacer la tour
     * @return true si c'est un mouvement authorizé par la tour
     */
    public boolean canMoveTo(Plate plate, int [] testedPosition){
        int [] actualPosition = this.getPosition(plate);
        if (actualPosition[0] == testedPosition[0] && actualPosition[1] == testedPosition[1]){
            return false;
        }
        if (actualPosition[0] == testedPosition[0] || actualPosition[1] == testedPosition[1]){
            return true;
        }
        return false;
    }
}
