package ru.sberbank.edu;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Do file statistic
 */
public class FileAnalyzer implements Statistic {


    @Override
    public int getLineCount() {
        int lineCount=0;
        try (BufferedReader reader = new BufferedReader(new FileReader("phones.txt"))) {
            String content;
            while((content =reader.readLine()) !=null){
                lineCount=lineCount+1;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineCount;
    }

    @Override
    public int getSpaceCount() {
        int spaceCount=0;
        try (BufferedReader reader = new BufferedReader(new FileReader("phones.txt"))) {
            String content;
            Pattern pattern = Pattern.compile("\\s+");
            while ((content = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(content);
                while (matcher.find()) {
                    spaceCount++;
                }
            }
            } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return spaceCount;
    }

    @Override
    public String getLongestLine() {
        int maxLength=0;
        String longestLine=null;
        try (BufferedReader reader = new BufferedReader(new FileReader("phones.txt"))) {
            int contentLength;
            String content;
            while ((content = reader.readLine()) != null) {
        contentLength=content.length();
        if(contentLength>maxLength){
            maxLength=contentLength;
            longestLine="The longest line in phones.txt: "+content;
        }
        }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return longestLine;
    }

    @Override
    public void save(int lineCount, int spaceCount, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("fileWithAnalyze.txt"))) {
            writer.write("Amount of line:" + lineCount + "\n");
            writer.write("Amount of spaces: " +spaceCount+ "\n");
            writer.write("The longest line of file: " +line+ "\n");
        } catch (IOException e) {
            System.out.println("Cannot create file"+e.getMessage());
        }
    }
}