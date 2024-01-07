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
public class King extends Piece{

    /**
     * 
     * @param colourPiece
     * @param typePiece 
     */
    King(colourPiece colour) {
        super(colour, Piece.pieceType.KING);
    }
    
    /**
     * Test si le roi a le droit de se déplacer de sa position actuelle à une nouvelle position à tester
     * @param plate : plateau sur lequel sont les pièces
     * @param testedPosition : la position sur laquelle on souhaite déplacer le roi
     * @return true si c'est un mouvement autorisé par le roi
     */
    public boolean canMoveTo(Plate plate, int [] testedPosition){
        int [] actualPosition = plate.getPositionPiece(this);
        if ((Math.abs(actualPosition[0] - testedPosition[0]) == 1 && Math.abs(actualPosition[1] - testedPosition[1]) == 1) 
                || (actualPosition[0] == testedPosition[0] && Math.abs(actualPosition[1] - testedPosition[1]) == 1)
                || (actualPosition[1] == testedPosition[1] && Math.abs(actualPosition[0] - testedPosition[0]) == 1)){
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
    							{0, 1},
    							{1, 1},
    							{1, 0},
    							{1, -1},
    							{0, -1},
    							{-1, -1},
    							{-1, 0},
    							{-1, 1}
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
