package ru.sberbank.edu;

import java.io.*;

/**
 * Create file
 */
public class FileGenerator {
    public void generateFile(String iPhone,String samsung,String xiaomi){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("phones.txt"))) {
            writer.write(iPhone + "\n");
            writer.write(samsung + "\n");
            writer.write(xiaomi + "\n");
        } catch (IOException e) {
            System.out.println("Cannot create file"+e.getMessage());
        }
    }
//    String iPhone;
//    String Samsung;
//    String Xiaomi;
    /* Реализовал данный метод чтобы содержание файла можно было генерировать
     public void writeInfoAboutBestThreeBrands() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        {
            try {
                System.out.println("Write info about iPhone: ");
                iPhone = "iPhone INFO: "+reader.readLine();
                System.out.println("Write info about Samsung: ");
                Samsung = "Samsung INFO: "+reader.readLine();
                System.out.println("Write info about Xiaomi: ");
                Xiaomi = "Xiaomi INFO: "+reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

     */




}
