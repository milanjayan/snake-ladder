package org.example.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Piece {
    private Color color;
    private Cell position;
}
