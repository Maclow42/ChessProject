/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author PCdePret_2
 */
import java.util.ArrayList;
import java.util.List;

public class Plate {
    //Echiquier : matrice 8*8
    public Piece [][] matrixPlate;
    //Prochain jour à jouer. Les blancs (true) commencent
    private boolean nextToPlay;
    
    /**
     * type de pièce :
     * 0 : pion
     * 1 : tour
     * 2 : cavalier
     * 3 : fou
     * 4 : dame
     * 5 : roi
     */
    
    /**
     * Constructeur
     */
    public Plate(){
        this.nextToPlay = true;
        this.matrixPlate = new Piece[8][8];
        
        //Pieces noirs
        matrixPlate[0][0] = new Rook(false); 
        matrixPlate[1][0] = new Knight(false);
        matrixPlate[2][0] = new Bishop(false);
        matrixPlate[3][0] = new Queen(false);
        matrixPlate[4][0] = new King(false);
        matrixPlate[5][0] = new Bishop(false);
        matrixPlate[6][0] = new Knight(false);
        matrixPlate[7][0] = new Rook(false);
        //Pions noirs
        for(int i = 0; i <= 7; i++)
        	matrixPlate[i][1] = new Pawn(false);
        
        //Pieces blanches
        matrixPlate[0][7] = new Rook(true);
        matrixPlate[1][7] = new Knight(true);
        matrixPlate[2][7] = new Bishop(true);
        matrixPlate[3][7] = new Queen(true);
        matrixPlate[4][7] = new King(true);
        matrixPlate[5][7] = new Bishop(true);
        matrixPlate[6][7] = new Knight(true);
        matrixPlate[7][7] = new Rook(true);
        //Pions blancs
        for(int i = 0; i <= 7; i++)
        	matrixPlate[i][6] = new Pawn(true);
    }
    
    /**
     * 
     * @param piece
     * @param position 
     */
    public boolean canMovePiece(Piece piece,int[] positionToGo){
        
        //Position sur laquelle le joueur souhaite déplacer sa pièce
        int x2 = positionToGo[0];
        int y2 = positionToGo[1]; 
        
        //Verifie si le mouvement est possible
        if (piece.canMoveTo(this, positionToGo) == true) {
        //Verifie si la case demandée est bien dans le plateau
            if ((x2 >= 0 && x2 <= 7) && (y2 >= 0 && y2 <= 7)){
                if (getPieceBetween(piece, positionToGo) == false){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean movePiece(Piece piece, int[] positionToGo){
        //Position sur laquelle le joueur souhaite déplacer sa pièce
        int x2 = positionToGo[0];
        int y2 = positionToGo[1]; 
        
        //Position de depart de la pièce à déplacer
        int[] actualPosition = getPositionPiece(piece); 
        int x1 = actualPosition[0];
        int y1 = actualPosition[1];
        
        if (canMovePiece(piece,positionToGo) == true){
            if (matrixPlate[x2][y2] != null){ 
                //matrixPlate[x][y].etat = false;
            }
            matrixPlate[x2][y2] = piece;
            matrixPlate[x1][y1] = null; //On degage la piece si elle est bougée
            nextToPlay = !nextToPlay; //On inverse NextToPlay
            return true;  
        }
        return false;  
    }
    
    /**
     * Getteur pour la position d'une pièce
     * @param piece
     * @return un tableau d'entiers contenant les deux coordonnées
     */
    public int[] getPositionPiece(Piece piece) {
        for (int i = 0; i < matrixPlate.length; i++) {
            for (int j = 0; j < matrixPlate[i].length; j++) {
                if (matrixPlate[i][j] == piece) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Retourne null si l'objet n'est pas trouvé
    }
    
    public boolean getPieceBetween(Piece piece, int[] positionToGo){
        //Position de depart de la pièce à déplacer
        int[] actualPosition = getPositionPiece(piece); 

        List<int[]> positionsBetween = getPositionsBetween(actualPosition,positionToGo);
        
        for (int[] position : positionsBetween) {
            int x = position[0];
            int y = position[1];
            
            if (matrixPlate[x][y] != null) {
                return true;
            }
        }

        return false;
    }
    
    public List<int[]> getPositionsBetween(int[] actualPosition,int[] positionToGo){
        int x2 = positionToGo[0];
        int y2 = positionToGo[1]; 
 
        int x1 = actualPosition[0];
        int y1 = actualPosition[1];
        
        List<int[]> positionsBetween = new ArrayList<>();
        
        if (x1 == x2) { // Meme collone
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);
            for (int y = minY + 1; y < maxY; y++) {
                positionsBetween.add(new int[]{x1, y});
            }
        }
        
        else if (y1 == y2) { // Meme ligne
            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            for (int x = minX + 1; x < maxX; x++) {
                positionsBetween.add(new int[]{x, y1});
            }     
        }
                
        else if ((x2 > x1 && y2 > y1) || (x2 < x1 && y2 < y1)){ //Premiere diagonale
            int minX = Math.min(x1, x2);
            int minY = Math.min(y1, y2);
            int maxX = Math.max(x1, x2);
            for (int i = 1; i < maxX - minX; i++) {
                positionsBetween.add(new int[]{minX + i, minY + i});
            }   
        }
        
        else if ((x2 > x1 && y2 < y1) || (x2 < x1 && y2 > y1)){ //Seconde diagonale
            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            int maxY = Math.min(y1, y2);
            for (int i = 1; i < maxX - minX; i++) {
                positionsBetween.add(new int[]{minX + i, maxY - i});
            }  
        }
        
        return positionsBetween; 
    }
    
}
    
    