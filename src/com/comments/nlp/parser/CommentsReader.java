package com.comments.nlp.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.comments.nlp.utilities.Strings;

/**
 * CommentsReader class
 * 
 * @author Edward Werner
 *
 */
public class CommentsReader {

  public CommentsReader(String source) {
    try {
      walkFilepath(source);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void walkFilepath(String path) throws IOException {
    File root = new File(path);
    File[] list = root.listFiles();

    if (list == null) {
      return;
    }
    int counter = 0;
    for (File f : list) {
      if (f.isDirectory()) {
        walkFilepath(f.getAbsolutePath());
      } else {
        String file = f.getAbsolutePath().toString();
        if (file.endsWith(".java")) {
          try {
            System.out.println(counter);
            openFile(file);
            counter++;
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  private void openFile(String fileName) throws IOException {
    
    String line = null;

    FileReader fileReader = new FileReader(fileName);
    BufferedReader bufferedReader = new BufferedReader(fileReader);

    
    // Regex pattern for java comments
//    String pattern = "(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|(//.*)";
//    String pattern = "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)";
//    String pattern = "/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/";
    String pattern = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/";

    // Create a pattern object
    Pattern p = Pattern.compile(pattern);
    BufferedWriter writer = new BufferedWriter(new FileWriter(Strings.JAVA_COMMENTS));
    
    try {
      while ((line = bufferedReader.readLine()) != null) {
//        String clean = line.replaceAll("//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/", "$1 ");
        
        // Create matcher object
        Matcher match = p.matcher(line);
        
        // Find a pattern match
        if (match.matches()) {
//          System.out.println(clean);
          System.out.println(line);
          
          
          // Write line to file
          writer.write(line);
        }
      }
      bufferedReader.close();
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}