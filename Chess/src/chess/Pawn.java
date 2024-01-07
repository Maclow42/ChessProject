/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author PCdePret_2
 */
public class Pawn extends Piece{
    /**
     * Constructeur
     * @param colourPiece
     * @param typePiece 
     */
    Pawn (colourPiece colour) {
        super(colour, Piece.pieceType.PAWN);
    }
    
    /**
     * Test si le pion a le droit de se déplacer de sa position actuelle à une nouvelle position à tester
     * @param plate : plateau sur lequel sont les pièces
     * @param testedPosition : la position sur laquelle on souhaite déplacer le pion
     * @return true si c'est un mouvement autorisé par le pion
     */
    public boolean canMoveTo(Plate plate, int [] testedPosition){
        //récupère la position du pion
        int [] actualPosition = this.getPosition(plate);
         /**
         * Les mouvements étant toujours vers l'avant pour les pions, 
         * il faut savoir si c'est un pion noir ou blanc pour savoir comment il peut bouger.
         * On sépare donc les cas : pion blanc et pion noir
         */
        //si le pion est noir
        if (colour == Piece.colourPiece.BLACK){
            //teste si la position testée est juste devant le pion
            if ((actualPosition[0] == testedPosition[0]) && (actualPosition[1] == testedPosition[1] - 1)){
                return true;
            }
            //teste si la position testée est juste en diagonale du pion
            if((Math.abs(actualPosition[0] - testedPosition[0]) == 1) && (actualPosition[1] == testedPosition[1] - 1)){
                return true;
            }
            /**
             * teste si c'est le premier coup du pion.
             * Si c'est le cas, le pion est autorisé à avancer de 2 cases
             */
            if(actualPosition[1] == 1 && (actualPosition[0] == testedPosition[0]) && (actualPosition[1] == testedPosition[1] - 2)){
                return true;
        }
            
        }
        //si le pion es blanc
        else {
            //teste si la position testée est juste devant le pion
            if ((actualPosition[0] == testedPosition[0]) && (actualPosition[1] == testedPosition[1] + 1)){
                return true;
            }
            //teste si la position testée est juste en diagonale du pion
            if((Math.abs(actualPosition[0] - testedPosition[0]) == 1) && (actualPosition[1] == testedPosition[1] + 1)){
                return true;
            }
            /**
             * teste si c'est le premier coup du pion.
             * Si c'est le cas, le pion est autorisé à avancer de 2 cases
             */
            if(actualPosition[1] == 6 && ((actualPosition[0] == testedPosition[0]) && (actualPosition[1] == testedPosition[1] + 2))){
                return true;
            }
        }
    return false;
    }
    

    
}
