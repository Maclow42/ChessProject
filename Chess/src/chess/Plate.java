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
import chess.Piece.*;

public class Plate {
    //Echiquier : matrice 8*8
    public  Piece [][] matrixPlate;
    //Prochain jour à jouer. Les blancs (true) commencent
    private colourPiece nextToPlay;
    
    private static int[] posKingB = new int[]{4,0}; //Position du roi noir
    private static int[] posKingW = new int[]{4,7}; //Position du roi blanc
    
    /**
     * Constructeur
     */
    public Plate(){
        this.nextToPlay = colourPiece.WHITE;
        this.matrixPlate = new Piece[8][8];
        
        
        //Pieces noirs
        matrixPlate[0][0] = new Rook(colourPiece.WHITE); 
        matrixPlate[1][0] = new Knight(colourPiece.WHITE);
        matrixPlate[2][0] = new Bishop(colourPiece.WHITE);
        matrixPlate[3][0] = new Queen(colourPiece.WHITE);
        matrixPlate[4][0] = new King(colourPiece.WHITE);
        this.posKingB = new int[]{4,0};
        matrixPlate[5][0] = new Bishop(colourPiece.WHITE);
        matrixPlate[6][0] = new Knight(colourPiece.WHITE);
        matrixPlate[7][0] = new Rook(colourPiece.WHITE);
        //Pions noirs
        for(int i = 0; i <= 7; i++)
        	matrixPlate[i][1] = new Pawn(colourPiece.WHITE);
        
        //Pieces blanches
        matrixPlate[0][7] = new Rook(colourPiece.BLACK);
        matrixPlate[1][7] = new Knight(colourPiece.BLACK);
        matrixPlate[2][7] = new Bishop(colourPiece.BLACK);
        matrixPlate[3][7] = new Queen(colourPiece.BLACK);
        matrixPlate[4][7] = new King(colourPiece.BLACK);
        this.posKingW = new int[]{4,7};
        matrixPlate[5][7] = new Bishop(colourPiece.BLACK);
        matrixPlate[6][7] = new Knight(colourPiece.BLACK);
        matrixPlate[7][7] = new Rook(colourPiece.BLACK);
        //Pions blancs
        for(int i = 0; i <= 7; i++)
        	matrixPlate[i][6] = new Pawn(colourPiece.BLACK);
    }    
    
    /**
     * Verification de possibilité de mouvement
     * @param piece a bouger
     * @param actualPosition position de la piece
     * @param positionToGo position visée
     * @param twoTest est que c'est ou non un second test( appeler via la methode getChecked)
     * @return vraie ou faux si on peut ou non bouger la piéce
     */
    public boolean canMovePiece(Piece piece,int[] actualPosition, int[] positionToGo, boolean twoTest){
        //Alternance du tour de jeu
        if ((piece.colour == nextToPlay) || (twoTest == true)){
            //Position sur laquelle le joueur souhaite déplacer sa pièce
            int x2 = positionToGo[0];
            int y2 = positionToGo[1]; 

            //Verifie si le mouvement est possible
            if (piece.canMoveTo(this, positionToGo) == true) {
                //Verifie si la case demandée est bien dans le plateau
                if ((x2 >= 0 && x2 <= 7) && (y2 >= 0 && y2 <= 7)){

                    //On verifie que le pion ne mange pas devant lui
                    if (piece.type == pieceType.PAWN){
                        if (matrixPlate[x2][y2] != null){
                            if ((matrixPlate[x2][y2].colour != piece.colour) && (actualPosition[0] == x2)){
                                return false;
                            }
                        }
                    }

                    if ((matrixPlate[x2][y2] == null) || (matrixPlate[x2][y2].colour != piece.colour)){ //Case libre ou mangeable
                        int x1 = actualPosition[0];
                        int y1 = actualPosition[1]; 

                        if (getPieceBetween(actualPosition, positionToGo) == false){ //Pas de piece entre les deux positions
                            //On verifie si le mouvement ne met pas en echec via un deplacement virtuel (uniquement si c'est le premier test)
                            if (twoTest == false){
                                Piece toPiece = matrixPlate[x2][y2]; //Piece ou on va se deplacer
                                matrixPlate[x2][y2] = piece;
                                matrixPlate[x1][y1] = null;

                                boolean checked = false; //Initialistation de la variable de mise en echec

                                if (piece.colour == colourPiece.WHITE){ //Blanc
                                    if (piece.type != pieceType.KING){
                                        checked = getCheck(posKingW);
                                    }else{
                                        checked = getCheck(new int[]{x2,y2});
                                    }

                                    if (checked == false){
                                        matrixPlate[x2][y2] = toPiece;
                                        matrixPlate[x1][y1] = piece;
                                        return true;
                                    }

                                }else{ //Noir
                                    if (piece.type != pieceType.KING){
                                        checked = getCheck(posKingB);
                                    }else{
                                        checked = getCheck(new int[]{x2,y2});
                                    }

                                    if (checked == false){
                                        matrixPlate[x2][y2] = toPiece;
                                        matrixPlate[x1][y1] = piece;
                                        return true;
                                    }     
                                }

                                matrixPlate[x2][y2] = toPiece;
                                matrixPlate[x1][y1] = piece;
                            }else{
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Deplace le piece
     * @param piece a bouger
     * @param positionToGo position visée
     * @return vrai ou faux si on a pu deplacer la piece
     */
    public boolean movePiece(Piece piece, int[] positionToGo){
        //Position sur laquelle le joueur souhaite déplacer sa pièce
        int x2 = positionToGo[0];
        int y2 = positionToGo[1]; 
        
        //Position de depart de la pièce à déplacer
        int[] actualPosition = getPositionPiece(piece); 
        int x1 = actualPosition[0];
        int y1 = actualPosition[1];
        
        if (canMovePiece(piece,actualPosition,positionToGo, false) == true){            
            if (piece.colour == colourPiece.WHITE){ //Blanc
                if (piece.type == pieceType.KING){ //On stock la position du roi
                    posKingW = new int[]{x2,y2};
                }
                
                matrixPlate[x2][y2] = piece;
                matrixPlate[x1][y1] = null; //On degage la piece si elle est bougée
                nextToPlay = (nextToPlay == colourPiece.BLACK) ? colourPiece.WHITE : colourPiece.BLACK; //On inverse NextToPlay
                
                if (getCheckMate(posKingB) == true){
                    System.out.println("Les blancs ont win");
                }

            }else{ //Noir
                if (piece.type == pieceType.KING){ //On stock la position du roi
                    posKingB = new int[]{x2,y2};
                }
                
                matrixPlate[x2][y2] = piece;
                matrixPlate[x1][y1] = null; //On degage la piece si elle est bougée
                nextToPlay = (nextToPlay == colourPiece.BLACK) ? colourPiece.WHITE : colourPiece.BLACK; //On inverse NextToPlay
                
                if (getCheckMate(posKingW) == true){
                    System.out.println("Les noirs ont win");
                }
            }
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
    
    /**
     * Permet de savoir si une piece bloque dans la liste obtenue avec getPositionsBetween
     * @param actualPosition position de la piece a bouger
     * @param positionToGo position visée
     * @return vraie ou faux selon la presence de piece entre les deux pieces
     */
    public boolean getPieceBetween(int[] actualPosition, int[] positionToGo){
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
    /**
     * Getteur d'une liste vde position entre deux positions
     * @param actualPosition position de depart
     * @param positionToGo position d'arrivée
     * @return une liste de list d'entier [x,y]
     */
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
        else if (Math.abs(actualPosition[0] - positionToGo[0]) == Math.abs(actualPosition[1] - positionToGo[1])){ //On verifie que c'est bien sur une diagonale
            if ((x2 > x1 && y2 > y1) || (x2 < x1 && y2 < y1)){ //Premiere diagonale
                int minX = Math.min(x1, x2);
                int minY = Math.min(y1, y2);
                int maxX = Math.max(x1, x2);
                for (int i = 1; i < maxX - minX; i++) {
                    positionsBetween.add(new int[]{minX + i, minY + i});
                }   
            }

            if ((x2 > x1 && y2 < y1) || (x2 < x1 && y2 > y1)){ //Seconde diagonale
                int minX = Math.min(x1, x2);
                int maxX = Math.max(x1, x2);
                int maxY = Math.max(y1, y2);
                for (int i = 1; i < maxX - minX; i++) {
                    positionsBetween.add(new int[]{minX + i, maxY - i});
                }  
            }
        }
        return positionsBetween; 
    }

    /**
     * @param positionK position du roi
     * @return vraie si le roi est en echec et faux sinon
     */
    public boolean getCheck(int[] positionK){
        //Position du roi
        int x = positionK[0];
        int y = positionK[1];   
        
        for (int i = 0; i < matrixPlate.length; i++) {
            for (int j = 0; j < matrixPlate[i].length; j++) {
                
                if (matrixPlate[i][j] != null){ //C'est une piece
                    if (canMovePiece(matrixPlate[i][j], new int[]{i,j}, positionK,true) == true){ //Elle peut manger le roi
                        return true;
                    }
                }
                
            }
        }
        
        return false;
    }
    
    /**
     * Renvoie une liste des positions 9 positions entourant une positions une position initiale( + la position initiales) si elles sont dans le tableau
     * @param position position initiale
     * @return 
     */
    public int[][] getSurroundingPositions(int[] position) {
        int x = position[0];
        int y = position[1];   
        
        int[][] surroundingPositions = new int[9][2];
        int count = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < 8 && j >= 0 && j < 8) {
                    surroundingPositions[count][0] = i;
                    surroundingPositions[count][1] = j;
                    count++;
                }else{
                    surroundingPositions[count][0] = 9; // 9 = valeur en dehors
                    surroundingPositions[count][1] = 9;
                    count++;         
                }
            }
        }
        return surroundingPositions;
    }
    
    /**
     * Verifie si un advesaire est ou non en echec et maths
     * @param positionK positon du roi blanc ou noir
     * @return true si le roi est en echec et maths
     */
    public boolean getCheckMate(int[] positionK){ //Marche pas car ne prend pas en compte le fais qu'une piece puisse bouger pour proteger le roi
        //On commence par verifier que le roi est en echec
        if (getCheck(positionK) == true){ 
            int[][] avaiblePos = getSurroundingPositions(positionK);
            
            //Verifie si on peut sortire de l'echec en bougeant le roi
            for (int i = 0; i < avaiblePos.length; i++) {
                int x1 = positionK[0];
                int y1 = positionK[1];               
                
                int x2 = avaiblePos[i][0];
                int y2 = avaiblePos[i][1];
                //On ne prend que les positions dans le tableau
                if (x2 != 9){ 
                    // Soit la case est vide soit elle est mangeable
                    if ((matrixPlate[x2][y2] == null) || (matrixPlate[x2][y2].colour != matrixPlate[x1][y1].colour)){
                        //On verfifie si la case de destination n'est pas en echec via un deplacement virtuel
                        Piece stockedPiece = matrixPlate[x2][y2];
                        matrixPlate[x2][y2] = matrixPlate[x1][y1];  
                        matrixPlate[x1][y1] = null;
                        
                        if (getCheck(avaiblePos[i]) == false){
                            matrixPlate[x1][y1] = matrixPlate[x2][y2];
                            matrixPlate[x2][y2] = stockedPiece;
                            return false;
                        }
                        matrixPlate[x1][y1] = matrixPlate[x2][y2];
                        matrixPlate[x2][y2] = stockedPiece;
                    }
                }
            }
            
            //On verifie si on peut empecher l'echec en bougeant une piece
            
            
            
            
            return true;
        }else{
            return false;
        }
        
    }
    
    
}
    
    



