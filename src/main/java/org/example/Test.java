package org.example;

import org.example.models.Color;
import org.example.models.Piece;
import org.example.registries.PiecesRegistry;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.SnakeAndLadder.scanner;

public class Test {
    public static void main(String[] args) {
        PiecesRegistry piecesRegistry = PiecesRegistry.getInstance();
        List<Piece> pieces = new ArrayList<>();
        while(true) {
            int option = 0;
            System.out.println("Choose piece");
            System.out.println(option+" exit");
            option++;
            Map<Integer, Color> tempColorMap = new HashMap<>();
            for(Color color : piecesRegistry.getPiecesMap().keySet()) {
                System.out.println(option+". "+color);
                tempColorMap.put(option, color);
                option++;
            }
            int choice = scanner.nextInt();
            if(choice == 0) {
                if(pieces.isEmpty()) {
                    System.out.println("choose minimum one piece");
                    continue;
                } else {
                    break;
                }
            }
            switch (tempColorMap.get(choice)) {
                case VIOLET: pieces.add(piecesRegistry.getPiece(Color.VIOLET));
                    break;
                case INDIGO: pieces.add(piecesRegistry.getPiece(Color.INDIGO));
                    break;
                case BLUE: pieces.add(piecesRegistry.getPiece(Color.BLUE));
                    break;
                case GREEN: pieces.add(piecesRegistry.getPiece(Color.GREEN));
                    break;
                case YELLOW: pieces.add(piecesRegistry.getPiece(Color.YELLOW));
                    break;
                case ORANGE: pieces.add(piecesRegistry.getPiece(Color.ORANGE));
                    break;
                case RED: pieces.add(piecesRegistry.getPiece(Color.RED));
                    break;
                default:
                    System.out.println("wrong option");
            }
        }
        for(Piece piece : pieces) {
            System.out.print(piece.getColor()+" ");
        }
    }
}
