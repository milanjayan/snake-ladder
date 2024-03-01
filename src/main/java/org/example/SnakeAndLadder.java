package org.example;


import org.example.models.*;
import org.example.registries.PiecesRegistry;

import java.util.*;

public class SnakeAndLadder {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("<----------- SNAKE N' LADDER ----------->");
        System.out.println("Board size? ");
        int size = scanner.nextInt();
        scanner.nextLine();
        Board board = new Board(size);
        board.printSnakesAndLadders();
        System.out.println("Number of players? ");
        int playerCount = scanner.nextInt();
        scanner.nextLine();
        List<Player> players = getPlayers(playerCount);
        LeaderBoard leaderBoard = LeaderBoard.builder()
                .playerRankings(new ArrayList<PlayerRanking>()).build();
        Dice dice = new Dice();
        Game game = Game.builder().board(board)
                .players(players)
                .leaderBoard(leaderBoard)
                .dice(dice)
                .build();
        game.start();
    }

    private static List<Player> getPlayers(int playerCount) {
        PiecesRegistry piecesRegistry = PiecesRegistry.getInstance();
        List<Player> players = new ArrayList<>();
        for(int i=0; i<playerCount; i++) {
            System.out.println("Player "+(i+1));
            System.out.print("Name? ");
            String name = scanner.nextLine();
            List<Piece> pieces = new ArrayList<>();
            while(true) {
                int option = 1;
                System.out.println("Choose piece");
                System.out.println("0. exit");
                Map<Integer, Color> tempColorMap = new HashMap<>();
                for(Color color : piecesRegistry.getPiecesMap().keySet()) {
                    System.out.println(option+". "+color);
                    tempColorMap.put(option, color);
                    option++;
                }
                int choice = scanner.nextInt();
                scanner.nextLine();
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
            Player player = Player.builder().name(name).pieces(pieces).build();
            players.add(player);
        }
        return players;
    }

}
