package org.example.models;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class Cell {
    private int start;
    private int end;

    public Piece move(Piece piece, Board board) {
        Cell position = board.getCells().get(end);
        piece.setPosition(position);
        return piece;
    }
}
