/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author PCdePret_2
 */
public class King extends Piece{

    /**
     * 
     * @param colourPiece
     * @param typePiece 
     */
    King(boolean colourPiece, int typePiece) {
        super(colourPiece, typePiece);
    }
    
    /**
     * Test si le roi a le droit de se déplacer de sa position actuelle à une nouvelle position à tester
     * @param plate : plateau sur lequel sont les pièces
     * @param testedPosition : la position sur laquelle on souhaite déplacer le roi
     * @return true si c'est un mouvement authorizé par le roi
     */
    boolean canMoveTo(Piece [] plate, int [] testedPosition){
        int [] actualPosition = this.getPosition(plate);
        if (actualPosition == testedPosition){
            return false;
        }
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                int [] authorizedPosition = {actualPosition[0] - 1 + i, actualPosition[1] -1 + j};
                if (testedPosition == authorizedPosition){
                    return true;
                }
            }
        }
        return false;
    }
                                                                                                           
        
}
