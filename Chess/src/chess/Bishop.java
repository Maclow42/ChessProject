/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

import java.util.ArrayList;

/**
 *
 * @author PCdePret_2
 */
public class Bishop extends Piece{

    /**
     * Construvteur
     * @param colourPiece
     * @param typePiece 
     */
    Bishop (colourPiece colour) {
        super(colour, Piece.pieceType.BISHOP);
    }
    
    /**
     * Test si la tour a le droit de se déplacer de sa position actuelle à une nouvelle position à tester
     * @param plate : plateau sur lequel sont les pièces
     * @param testedPosition : la position sur laquelle on souhaite déplacer la tour
     * @return true si c'est un mouvement autorisé par la tour
     */
    public boolean canMoveTo(Plate plate, int [] testedPosition){
        int [] actualPosition = this.getPosition(plate);
        if (actualPosition[0] == testedPosition[0] && actualPosition[1] == testedPosition[1]){
            return false;
        }
        if (Math.abs(actualPosition[0] - testedPosition[0]) == Math.abs(actualPosition[1] - testedPosition[1])){
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
    	
    	int i = 1;
    	while(cx+i < 8 && cy+i < 8) {
    		if(plate.matrixPlate[cx+i][cy+i] == null)
    			result.add(new int[] {cx+i, cy+i});
    		else if(plate.matrixPlate[cx+i][cy+i].colour != colour) {
    			result.add(new int[] {cx+i, cy+i});
    			break;
    		}	
    	}
    	
    	i = 1;
    	while(cx+i < 8 && cy-i >= 0) {
    		if(plate.matrixPlate[cx+i][cy-i] == null)
    			result.add(new int[] {cx+i, cy-i});
    		else if(plate.matrixPlate[cx+i][cy-i].colour != colour) {
    			result.add(new int[] {cx+i, cy-i});
    			break;
    		}	
    	}
    	
    	i = 1;
    	while(cx-i >= 8 && cy-i >= 0) {
    		if(plate.matrixPlate[cx-i][cy-i] == null)
    			result.add(new int[] {cx-i, cy-i});
    		else if(plate.matrixPlate[cx-i][cy-i].colour != colour) {
    			result.add(new int[] {cx-i, cy-i});
    			break;
    		}	
    	}
    	
    	i = 1;
    	while(cx-i >= 8 && cy+i < 8) {
    		if(plate.matrixPlate[cx-i][cy+i] == null)
    			result.add(new int[] {cx-i, cy+i});
    		else if(plate.matrixPlate[cx-i][cy+i].colour != colour) {
    			result.add(new int[] {cx-i, cy+i});
    			break;
    		}	
    	}
    	
    	return result;
    }
}
