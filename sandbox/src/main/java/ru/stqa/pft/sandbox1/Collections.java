package ru.stqa.pft.sandbox1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String [] args)  {
    String[] langs = {"Java", "C#", "Python", "PHP"};

    List<String> languages = Arrays.asList ("Java", "C#", "Python", "PHP");

    /* for (String l : langs) {         for (int i = 0; i < langs.length; i++)
    for (int i = 0; i < languages.size(); i++)
      System.out.println("Я хочу выучить " + languages.get(i)); */

    for (String l : languages) {
      System.out.println("Я хочу выучить " + l);
    }
  }
}
