package org.example.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PlayerRanking {
    private Player player;
    private int rank;
}
