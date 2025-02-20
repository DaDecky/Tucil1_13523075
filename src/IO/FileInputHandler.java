package IO;

import game.Block;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInputHandler {
  private Integer N, M, P;
  private String S;
  private ArrayList<Block> blocks = new ArrayList<>();
  
  public FileInputHandler(String filename) {
    File f = new File(filename);
    // check whether file exist
    if (!f.exists()){
      System.out.println("Error: File " + filename + " does not exist");
      System.exit(1);
    }
    
    // Check first line whether in format of "N M P"
    try (Scanner sc = new Scanner(f)){
      // split based on single or multiple spaces
      String[] line_1 = sc.nextLine().split("\\s+");
      if (line_1.length != 3){
        throw new Exception("First line must be in format of 'N M P'");
      }

      // validate line array are all integers
      for (String s : line_1){
        if (!s.matches("\\d+")){
          throw new Exception("First line must be in format of 'N M P'");
        }
      } 



      // store N, M, P
      N = Integer.valueOf(line_1[0]);
      M = Integer.valueOf(line_1[1]);
      P = Integer.valueOf(line_1[2]);

      // validate N,M,P
      // N,M bigger than 0
      // P bigger than 0 and less than or equal to 26
      if (N <= 0 || M <= 0 || P <= 0 || P > 26){
        throw new Exception("Condition Not Met N, M, P > 0 and P <= 26");
      }
      
      // check whether line 2 is DEFAULT/CUSTOM/PYRAMID
      if (sc.hasNextLine()){
        S = sc.nextLine().trim();
        if (!S.equals("DEFAULT") && !S.equals("CUSTOM") && !S.equals("PYRAMID")){
          throw new Exception("Second line must be either DEFAULT/CUSTOM/PYRAMID");
        }
      } else {
        throw new Exception("Second line must be either DEFAULT/CUSTOM/PYRAMID");
      }

      //  Get all blocks
      char current_block_type;
      String line;
      ArrayList<String> block_lines = new ArrayList<>();  

      if (sc.hasNextLine()){
        line = sc.nextLine();
        current_block_type = line.trim().charAt(0);
        
        // check whether block type is valid and all characters are the same
        if (!isValidBlockType(current_block_type)){
          throw new Exception("Block type must be from A-Z");
        }

        if (!isSameBlockType(line, current_block_type)){
          throw new Exception("Block type must be same for all characters in line");
        }

        // add the line to block_lines
        block_lines.add(line);
      }
      else{
        throw new Exception("No block lines found");
      }

      // get the rest of the same type of block_lines, then create a block
      // repeat until no more new blocks

      while (sc.hasNextLine()){
        line = sc.nextLine();

        if (line.trim().charAt(0) != current_block_type){
          // create a block from block_lines
          blocks.add(new Block(block_lines));

          // clear block_lines
          block_lines.clear();
          // replace current_block_type
          current_block_type = line.trim().charAt(0);
        }

        // add the line to block_lines
        // validate
        if (!isSameBlockType(line, current_block_type)){
          throw new Exception("Block type must be same for all characters in line");
        }
          block_lines.add(line);
      }
    // create the last block
    blocks.add(new Block(block_lines));

    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      System.exit(1);
    }




    // System.out.println("N: " + this.N + " M: " + this.M + " P: " + this.P);

    
  }
  // Validator

  // valid block type
  public static boolean isValidBlockType(char block_type){
    // check whether from A-Z, not checking the availability of the block only for the type
    return block_type >= 65 && block_type <= 90;
  }


  public static boolean isSameBlockType(String line, char block_type){
    // trim whitespace
    line = line.trim();
    
    for (int i = 0; i < line.length(); i++){
      if (line.charAt(i) != block_type){
        return false;
      }
    }
    return true;
  }


  // Getters

  public Integer getN() {
    return N;
  }

  public Integer getM() {
    return M;
  }

  public Integer getP() {
    return P;
  }
  public String getS() {
    return S;
  }

  public ArrayList<Block> getBlocks(){
    return this.blocks;
  }
}



