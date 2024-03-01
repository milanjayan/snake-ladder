package org.example.registries;

import lombok.Getter;
import org.example.models.Cell;
import org.example.models.Color;
import org.example.models.NormalCell;
import org.example.models.Piece;

import java.util.HashMap;
import java.util.Map;

@Getter
public class PiecesRegistry {

    private static PiecesRegistry piecesRegistry = null;
    private Map<Color, Piece> piecesMap = initializePieces();

    private Map<Color, Piece> initializePieces() {
        Map<Color, Piece> pieceMap = new HashMap<>();
        Cell tempCell = NormalCell.builder().start(0).end(0).build();
        pieceMap.put(Color.VIOLET, Piece.builder().color(Color.VIOLET).position(tempCell).build());
        pieceMap.put(Color.INDIGO, Piece.builder().color(Color.INDIGO).position(tempCell).build());
        pieceMap.put(Color.BLUE, Piece.builder().color(Color.BLUE).position(tempCell).build());
        pieceMap.put(Color.GREEN, Piece.builder().color(Color.GREEN).position(tempCell).build());
        pieceMap.put(Color.YELLOW, Piece.builder().color(Color.YELLOW).position(tempCell).build());
        pieceMap.put(Color.ORANGE, Piece.builder().color(Color.ORANGE).position(tempCell).build());
        pieceMap.put(Color.RED, Piece.builder().color(Color.RED).position(tempCell).build());
        return pieceMap;
    }

    private PiecesRegistry() {}

    public static PiecesRegistry getInstance() {
        if(piecesRegistry == null) {
            piecesRegistry = new PiecesRegistry();
        }
        return piecesRegistry;
    }
    public Piece getPiece(Color color) {
        Piece piece = piecesMap.get(color);
        piecesMap.remove(color);
        return piece;
    }
}
