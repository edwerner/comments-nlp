package com.comments.nlp.main;

import com.comments.nlp.parser.CommentsReader;
import com.comments.nlp.utilities.Strings;
/**
 * Main class
 * 
 * @author Edward Werner
 *
 */
public class Main {

  public static void main(String[] args) {
      new CommentsReader(Strings.SPRING_SOURCE);
  }
}