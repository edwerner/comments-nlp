package com.comments.nlp.parser;

import java.io.File;

/**
 * CommentsReader class
 * 
 * @author Edward Werner
 *
 */
public class CommentsReader {

  public CommentsReader(String source) {
    walkFilepath(source);
  }

  private void walkFilepath(String path) {
    File root = new File(path);
    File[] list = root.listFiles();

    if (list == null) {
      return;
    }

    for (File f : list) {
      if (f.isDirectory()) {
        walkFilepath(f.getAbsolutePath());
        System.out.println("Dir:" + f.getAbsoluteFile());
      } else {
        System.out.println("File:" + f.getAbsoluteFile());
      }
    }
  }
}