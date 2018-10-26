package com.comments.nlp.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.comments.nlp.utilities.Strings;

public class DirectoryWalker {
  static void RecursivePrint(File[] arr, int index, int level) throws InterruptedException {
    // terminate condition
    if (index == arr.length)
      return;

    // for files
    if (arr[index].isFile()) {
      if (arr[index].getName().endsWith(".java")) {
        // System.out.println(arr[index].getName());
        try {
          openFile(arr[index].getAbsolutePath());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    // for sub-directories
    else if (arr[index].isDirectory()) {
      // System.out.println("[" + arr[index].getName() + "]");

      // recursion for sub-directories
      RecursivePrint(arr[index].listFiles(), 0, level + 1);
    }

    // recursion for main directory
    RecursivePrint(arr, ++index, level);
  }

  private static void openFile(String fileName) throws IOException {

//    String line = null;

//    FileReader fileReader = new FileReader(fileName);
//    BufferedReader bufferedReader = new BufferedReader(fileReader);

    // Regex pattern for java comments
//    String pattern = "(/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/)|(//.*)";
    // String pattern = "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)";
    // String pattern = "/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+/";
    // String pattern = "//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/";

    // Create a pattern object
//    Pattern p = Pattern.compile(pattern);
//    BufferedWriter writer = new BufferedWriter(new FileWriter(Strings.JAVA_COMMENTS));

    List<String> lines = Collections.emptyList();
    try {
      lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
      for (String line : lines) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // try {
    // while ((line = bufferedReader.readLine()) != null) {
    // // String clean =
    // // line.replaceAll("//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/", "$1
    // // ");
    //
    // // Create matcher object
    // Matcher match = p.matcher(line);
    //
    // // Find a pattern match
    // if (match.matches()) {
    // System.out.println("***********************************");
    // System.out.println(line);
    //
    // // Write line to file
    // writer.write(line);
    // }
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // } finally {
    // bufferedReader.close();
    // writer.close();
    // }
  }

  // Driver Method
  public static void main(String[] args) throws InterruptedException {

    // File object
    File maindir = new File(Strings.APACHE_ANT_SOURCE);

    if (maindir.exists() && maindir.isDirectory()) {
      // array for files and sub-directories
      // of directory pointed by maindir
      File arr[] = maindir.listFiles();

      System.out.println("**********************************************");
      System.out.println("Files from main directory : " + maindir);
      System.out.println("**********************************************");

      // Calling recursive method
      RecursivePrint(arr, 0, 0);
    }
  }
}