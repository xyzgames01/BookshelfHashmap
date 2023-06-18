package com.main.bookshelffinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.*;

public class BookshelfApplication extends Application {

    public static Bookshelf<String, Book> myBookshelf = new Bookshelf<>();



    @Override
    public void start(Stage stage) throws IOException {
        ImportBooks("src/books.csv");

        FXMLLoader fxmlLoader = new FXMLLoader(BookshelfApplication.class.getResource("bookshelf-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),975, 400);
        stage.setTitle("Bookshelf");
        stage.setScene(scene);
        stage.show();
    }

    public static void ImportBooks(String csv){
        System.out.println("Importing books.csv file...");

        CSVReader csvReader = null;

        try
        {
            csvReader = new CSVReaderBuilder(new FileReader(new File(csv))).withCSVParser(new CSVParserBuilder().withSeparator(',').build()).build();

            String line[];

            csvReader.readNext();

            int lineNum = 1;

            while ((line = csvReader.readNext()) != null)
            {


                Book book = new Book(line,lineNum);
                myBookshelf.put(line[0].replaceAll("^\"|\"$", "" ), book);
                lineNum++;
            }

        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        finally
        {
            try
            {
                csvReader.close();
            }
            catch(IOException ie)
            {
                System.out.println("Error occured while closing the CSVReader");
                ie.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}