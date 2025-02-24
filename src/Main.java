import IO.FileInputHandler;
import game.Block;
import game.Board;
import game.Game;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileInputHandler f = new FileInputHandler(args.length > 0 ? "./test/" + args[0] : "./test/test_1.txt");

        Integer N, M, P;
        String S;
        ArrayList<Block> blocks;

        N = f.getN();
        M = f.getM();
        P = f.getP();
        S = f.getS();
        blocks = f.getBlocks();

        Board b = new Board(N, M, S);
        Game g = new Game();
        long startTime = System.currentTimeMillis();
        Board result = g.solve(blocks, b);
        long endTime = System.currentTimeMillis();
        long timeDifference = endTime - startTime;

        if (result == null) {
            System.out.println("No solution found");
        } else {
            result.print();
            // FileOutputHandler output = new FileOutputHandler("output.txt");
            // output.write(result);
        }
        System.out.println();
        System.out.println();
        System.out.println("Banyak kasus yang ditinjau: " + g.iteration);
        System.out.println("Waktu pencarian: " + timeDifference + " ms");

        if (result != null) {
            String content = "";
            for (int i = 0; i < result.row; i++) {
                for (int j = 0; j < result.col; j++) {
                    content += result.board[i][j];
                }
                content += "\n";
            }
            // output.write(content);
            // new FileOutputHandler("output.txt", content);

            // output.write(String(result));
        }

        // b.placeAll(blocks.get(0));
        // b.placeAll(blocks.get(1));
        // int[] es = b.findEmptySpace();
        // int x = es[0];
        // int y = es[1];
        // b.placeAt(blocks.get(1).rotate90().rotate90(), x, y);
        // b.place(blocks.get(1).rotate90().rotate90());
        // b.print();

        // char[][] block = { { 'A', 'A', 'A' }, { ' ', 'A', ' ' } };
        // Block bl = new Block(block);
        // bl.print();
        // bl.rotate90().print();
        // bl.rotate90().flip().print();

    }
}