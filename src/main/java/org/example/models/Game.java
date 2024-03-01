package org.example.models;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static org.example.SnakeAndLadder.scanner;

@Builder
@Getter
public class Game {
    private Board board;
    private List<Player> players;
    private LeaderBoard leaderBoard;
    private Dice dice;

    public void start() {
        int turn = 0;
        while(true) {
            Player currPlayer = players.get(turn);
            System.out.println(currPlayer.getName()+"'s turn");
            System.out.println("Enter to roll dice!");
            scanner.nextLine();
            int rollVal = dice.roll();
            System.out.println(rollVal);
            board.printSnakesAndLadders();
            System.out.println();
            Piece piece = currPlayer.play();
            boolean valid = validate(rollVal, piece);
            if(valid) {
                int newPosition = piece.getPosition().getStart() + rollVal;
                Cell cell = board.getCells().get(newPosition);
                cell.move(piece, board);
            }
            displayPlayersStatuses();
            if(checkPlayerCompleted(currPlayer)) {
                leaderBoard.addPlayerRankings(currPlayer);
                players.remove(currPlayer);
            }
            if(players.size() == 1) {
                break;
            }
            turn = (turn+1)%players.size();
        }
        leaderBoard.addPlayerRankings(players.get(0));
        leaderBoard.display();
    }

    private boolean checkPlayerCompleted(Player currPlayer) {
        int finalCell = board.getSize();
        for(Piece piece : currPlayer.getPieces()) {
            if(piece.getPosition().getStart() != finalCell) {
                return false;
            }
        }
        return true;
    }

    private void displayPlayersStatuses() {
        for(Player player : players) {
            System.out.println("Player: "+player.getName());
            for(Piece piece : player.getPieces()) {
                System.out.println(piece.getColor()+" = "+piece.getPosition().getStart());
            }
        }
    }

    private boolean validate(int rollVal, Piece piece) {
        if(piece.getPosition().getStart() == 0 && rollVal != 1) {
            return false;
        }
        return piece.getPosition().getStart() == 0 || piece.getPosition().getStart() + rollVal <= board.getSize();
    }
}
