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

    public static enum colourPiece{
    	WHITE,
    	BLACK
    };
    
    protected colourPiece colour;
    
    protected boolean firstMove;
   
    
    /**
     * Constructeur
     * @param colourPiece
     * @param typePiece 
     */
    
    Piece(colourPiece colour, pieceType type){
        this.colour = colour;
        this.type = type;
        this.etat = true;
        this.firstMove = true;
    }
    
    int [] getPosition(Plate plate){
        return plate.getPositionPiece(this);
    }
    
    colourPiece getColourPiece(){
        return this.colour;
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
