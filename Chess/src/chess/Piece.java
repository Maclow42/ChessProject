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
    /**
     * type de pièce :
     * 0 : pion
     * 1 : tour
     * 2 : cavalier
     * 3 : fou
     * 4 : dame
     * 5 : roi
     */
    protected int typePiece;
   
    
    /**
     * Constructeur
     * @param colourPiece
     * @param typePiece 
     */
    
    Piece(boolean colourPiece, int typePiece){
        this.colourPiece = colourPiece;
        this.typePiece = typePiece;
        this.etat = true;
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
    
    int getTypePiece(){
        return typePiece;
    }
    
    public abstract boolean canMoveTo(Plate plate, int [] testedPosition);
}
