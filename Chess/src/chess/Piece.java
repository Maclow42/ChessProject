/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess;

/**
 *
 * @author PCdePret_2
 */
public abstract class Piece {
    
    // couleur de la pièce : true pour blanc, false pour noir
    protected boolean colourPiece;
    protected boolean etat;
    
    protected static enum pieceType{
    	PAWN,
    	ROOK,
    	KNIGHT,
    	BISHOP,
    	QUEEN,
    	KING
    };
    
    public static pieceType[] pieceTypeArray = pieceType.values();
    
    protected pieceType type;
    
    protected boolean firstMove;
   
    
    /**
     * Constructeur
     * @param colourPiece
     * @param typePiece 
     */
    
    Piece(boolean colourPiece, pieceType type){
        this.colourPiece = colourPiece;
        this.type = type;
        this.etat = true;
        this.firstMove = true;
    }
    
    int [] getPosition(Plate plate){
        return plate.getPositionPiece(this);
    }
    
    boolean getColourPiece(){
        return this.colourPiece;
    }
    
    boolean getEtat(){
        return etat;
    }
    
    pieceType getTypePiece(){
        return this.type;
    }
    
    public boolean isFirstMove(){
    	return this.firstMove;
    }
    
    public abstract boolean canMoveTo(Plate plate, int [] testedPosition);
}
