/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author DaDecky
 */
public class FileOutputHandler {
  public FileOutputHandler(String filename, String content) {
    try {
      FileWriter myWriter = new FileWriter("./output/" + filename);
      myWriter.write(content);
      myWriter.close();
      System.out.println("Berhasil menulis ke /output/" + filename);
    } catch (IOException e) {
      System.out.println("Error");
      e.printStackTrace();

    }
  }

}

// https://www.w3schools.com/java/java_files_create.asp
class CreateFile {
  public CreateFile(String filename) {
    try {
      File myObj = new File("./output/filename.txt");
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
