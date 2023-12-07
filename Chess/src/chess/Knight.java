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
public class Knight extends Piece{

    /**
     * Constructeur
     * @param colourPiece
     * @param typePiece 
     */
    Knight(boolean colourPiece, int typePiece) {
        super(colourPiece, typePiece);
    }
    
    /**
     * Test si le cavalier a le droit de se déplacer de sa position actuelle à une nouvelle position à tester
     * @param plate : plateau sur lequel sont les pièces
     * @param testedPosition : la position sur laquelle on souhaite déplacer le cavalier
     * @return true si c'est un mouvement autorisé par le cavalier
     */
    public boolean canMoveTo(Plate plate, int [] testedPosition){
        int [] actualPosition = this.getPosition(plate);
        if ((Math.abs(actualPosition[0] - testedPosition[0]) == 1 && Math.abs(actualPosition[1] - testedPosition[1]) == 2)
                ||(Math.abs(actualPosition[0] - testedPosition[0]) == 2 && Math.abs(actualPosition[1] - testedPosition[1]) == 1)){
            return true;
        }
        return false;
    }
    
}
