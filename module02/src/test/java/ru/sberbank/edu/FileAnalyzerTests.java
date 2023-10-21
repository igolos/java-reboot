package ru.sberbank.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for class FileAnalyzer
 */
public class FileAnalyzerTests {

    FileGenerator fileGenerator=new FileGenerator();
    FileAnalyzer analyzer=new FileAnalyzer();
    String iPhone;
    String samsung;
    String xiaomi;
    @Test
    public void getLongestLineSuccess(){
        iPhone="iPhone is the best camera in phones";
        samsung="Samsung is the best OS in phones";
        xiaomi="Xiaomi is the cheapest phone";
        fileGenerator.generateFile(iPhone,samsung,xiaomi);
       assertTrue(analyzer.getLongestLine().equals("The longest line in phones.txt: iPhone is the best camera in phones"));
       assertFalse(analyzer.getLongestLine().isEmpty());
    }
    @Test
    public void getSpacesCountSuccess(){
        iPhone="iPhone is";
        samsung="Samsung is";
        xiaomi="Xiaomi is ";
        fileGenerator.generateFile(iPhone,samsung,xiaomi);
        assertEquals(4,analyzer.getSpaceCount());
    }
    @Test
    public void getLineCountSuccess(){
        iPhone="iPhone is the best camera in phones";
        samsung="Samsung is the best OS in phones";
        xiaomi="Xiaomi is the cheapest phone";
        fileGenerator.generateFile(iPhone,samsung,xiaomi);
        assertEquals(3,analyzer.getLineCount());
    }
}
