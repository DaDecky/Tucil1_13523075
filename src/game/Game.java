package game;

import java.util.ArrayList;

public class Game {
  public int iteration = 0;

  public Game() {
  }

  public Board solve(ArrayList<Block> blocks, Board b) {
    // Debugging
    // System.out.println("---");
    // b.print();
    // System.out.println("----");

    if (b.findEmptySpace() == null && blocks.isEmpty()) {
      return b;
    }

    for (int i = 0; i < blocks.size(); i++) {
      Block cur_block = blocks.get(i);
      Block cur_copy = cur_block;

      while (cur_block != null) {
        iteration++;

        if (b.place(cur_block)) {
          blocks.remove(i);

          Board result = solve(blocks, b);
          if (result != null) {
            return result;
          }

          b.remove();
          blocks.add(i, cur_copy);
        }

        cur_block = cur_block.next();
      }
    }

    return null;
  }

}
