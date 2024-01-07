/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;
import java.lang.Math;
import java.util.ArrayList;

import chess.Piece.colourPiece;

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
    Knight(colourPiece colour) {
        super(colour, Piece.pieceType.KNIGHT);
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
    
    public static ArrayList<int[]> getAccessiblePositions(Plate plate, int[] currentPos) {
    	int cx = currentPos[0], cy = currentPos[1];
    	
    	if(plate.matrixPlate[cx][cy] == null)
    		return null;
    	
    	colourPiece colour = plate.matrixPlate[cx][cy].colour;
    	
    	ArrayList<int[]> result = new ArrayList<int[]>();
    	
    	int[][] relatives = {	
    							{1, 2},
    							{2, 1},
    							{2, -1},
    							{1, -2},
    							{-1, -2},
    							{-2, -1},
    							{-2, 1},
    							{-1, 2}
    						};
    	
    	for(int[] coords : relatives) {
    		if(cx + coords[0] < 0 || cx + coords[0] > 7)
    			continue;
    		if(cy + coords[1] < 0 || cy + coords[1] > 7)
    			continue;
    		if(plate.matrixPlate[cx + coords[0]][cy + coords[1]] == null || plate.matrixPlate[cx + coords[0]][cy + coords[1]].colour != colour)
    			result.add(new int[] {cx + coords[0], cy + coords[1]});
    	}
    	
    	return result;
    }
    
}
