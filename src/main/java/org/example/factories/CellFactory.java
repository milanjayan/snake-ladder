package org.example.factories;

import org.example.exceptions.WrongCellTypeException;
import org.example.models.Cell;
import org.example.models.LadderCell;
import org.example.models.NormalCell;
import org.example.models.SnakeCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Math.floor;
import static java.lang.Math.random;

public class CellFactory {
    public Cell createCell(int type, int start, Set<Integer> freeCells, int size) {
        int end;
        switch (type) {
            case 1 -> {
                return NormalCell.builder().start(start).end(start).build();
            }
            case 2 -> {
                freeCells.remove(start);
                end = getLadderEnd(start, freeCells, size);
                return LadderCell.builder().start(start).end(end).build();
            }
            case 3 -> {
                freeCells.remove(start);
                end = getSnakeEnd(start, freeCells, size);
                return SnakeCell.builder().start(start).end(end).build();
            }
            default -> throw new WrongCellTypeException("Cell types is wrong");
        }
    }

    private int getSnakeEnd(int start, Set<Integer> freeCells, int size) {
        List<Integer> possibleEnds = new ArrayList<>();
        for(int i=2; i<start; i++) {
            if(freeCells.contains(i)) {
                possibleEnds.add(i);
            }
        }
        int endIdx = getRandom(possibleEnds.size());
        int end = possibleEnds.get(endIdx);
        freeCells.remove(end);
        return end;
    }

    private int getLadderEnd(int start, Set<Integer> freeCells, int size) {
        List<Integer> possibleEnds = new ArrayList<>();
        for(int i=start+1; i<size; i++) {
            if(freeCells.contains(i)) {
                possibleEnds.add(i);
            }
        }
        int endIdx = getRandom(possibleEnds.size());
        int end = possibleEnds.get(endIdx);
        freeCells.remove(end);
        return end;
    }

    private int getRandom(int range) {
        return (int) floor(random() * range);
    }
}
