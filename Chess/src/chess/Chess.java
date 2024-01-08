/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chess;

/**
 *
 * @author PCdePret_2
 */
public class Chess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Plate plateau = new Plate();
        int [] testedPosition1 = {3,2};
        
        Piece p1 = plateau.matrixPlate[3][1];
        boolean test1 = plateau.movePiece(p1, testedPosition1);
        System.out.println(test1);
        
        
        
        
        /*
        Queen dame = new Queen(true, 4);
        boolean test2 = dame.canMoveTo(plateau, testedPosition1);
        System.out.println(test2);
        
        Bishop fou = new Bishop(true, 3);
        boolean test3 = fou.canMoveTo(plateau, testedPosition1);
        System.out.println(test3);
        
        Knight cavalier = new Knight(true, 2);
        boolean test4 = cavalier.canMoveTo(plateau, testedPosition1);
        System.out.println(test4);
        
        Rook tour = new Rook(true, 1);
        boolean test5 = tour.canMoveTo(plateau, testedPosition1);
        System.out.println(test5);
        
        Pawn pionBlanc = new Pawn(true, 0);
        boolean test6 = pionBlanc.canMoveTo(plateau, testedPosition1);
        System.out.println(test6);
        
        Pawn pionNoir = new Pawn(false, 0);
        boolean test7 = pionNoir.canMoveTo(plateau, testedPosition1);
        System.out.println(test7);
        */
        
    }
    
}