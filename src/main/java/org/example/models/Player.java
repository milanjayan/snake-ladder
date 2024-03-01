package org.example.models;

import lombok.Builder;
import lombok.Getter;
import org.example.exceptions.InvalidChoiceException;

import java.util.List;

import static org.example.SnakeAndLadder.scanner;

@Builder
@Getter
public class Player {
    private String name;
    private List<Piece> pieces;

    public Piece play() {
        displayPiecesStatus();
        System.out.println();
        System.out.println("Choose the piece to move");
        int choice = 1;
        for(Piece piece : pieces) {
            System.out.println(choice+". "+piece.getColor());
            choice++;
        }
        choice = scanner.nextInt();
        if(choice <= 0 || choice > pieces.size()) {
            throw new InvalidChoiceException("Choice should be in range 1 to "+pieces.size());
        }
        return pieces.get(choice-1);
    }

    private void displayPiecesStatus() {
        for(Piece piece : pieces) {
            System.out.println(piece.getColor()+" -> "+piece.getPosition().getStart());
        }
    }
}
