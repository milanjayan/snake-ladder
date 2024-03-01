package org.example.models;
import lombok.Getter;
import org.example.factories.CellFactory;

import java.util.*;

import static java.lang.Math.floor;
import static java.lang.Math.random;

@Getter
public class Board {
    private final int size;
    private List<Cell> cells = new ArrayList<>();
    private final int CELL_TYPES = 3;
    private final int MINIMUM_OBSTACLE_GAP = 2;

    public Board(int size) {
        this.size = size;
        this.cells = initializeCells(size);
    }

    public void printSnakesAndLadders() {
        for(Cell cell : this.cells) {
            int cellStart = cell.getStart();
            int cellEnd = cell.getEnd();
            if(cellStart > cellEnd) {
                System.out.println("SNAKE-/-> "+cellStart+" -> "+cellEnd);
            } else if(cellStart < cellEnd) {
                System.out.println("LADDERS# "+cellStart+" -> "+cellEnd);
            }
        }
    }


    private List<Cell> initializeCells(int size) {
        List<Cell> cells = new ArrayList<>();
        cells.add(NormalCell.builder().start(0).end(0).build());
        cells.add(NormalCell.builder().start(1).end(1).build());
        Set<Integer> freeCells = new HashSet<>();
        for(int i=2; i<=size; i++){
            freeCells.add(i);
        }
        CellFactory cellFactory = new CellFactory();
        int gapCount = 0;
        for(int i=2; i<size; i++) {
            int cellType = 1;
            Cell cell;
            if(gapCount == MINIMUM_OBSTACLE_GAP && freeCells.contains(i)) {
                cellType = getRandom(CELL_TYPES);
                cell = cellFactory.createCell(cellType, i, freeCells, size);
                gapCount = 0;
            } else {
                cell = cellFactory.createCell(cellType, i, freeCells, size);
                gapCount++;
            }
            cells.add(cell);
        }
        cells.add(cellFactory.createCell(1, size, freeCells, size));
        return cells;
    }

    private int getRandom(int range) {
        int offset = (int) floor(random() * range);
        return 1 + offset;
    }
}
