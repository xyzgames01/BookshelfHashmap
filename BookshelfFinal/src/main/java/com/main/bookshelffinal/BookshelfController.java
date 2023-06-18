package com.main.bookshelffinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.opencsv.*;
import javafx.scene.text.Text;


public class BookshelfController {

    @FXML
    private TextField booktitle,title, author, genre, description, rating, publisher, release_date;

    @FXML
    private Text output;

    private int bookLine;

    @FXML
    private ListView<String> bookinfo;
    ObservableList<String> bookdata = FXCollections.observableArrayList();

    @FXML
    protected void onSearch() {

        if (booktitle.getText().equals("")){
            output.setText("Error, No Search Input");
            return;
        }

        if (BookshelfApplication.myBookshelf.get(booktitle.getText()) == null){
            output.setText("Error, No Such Book Exists");
            return;
        }

        output.setText("");

        bookdata.clear();
        bookinfo.getItems().clear();

        Book book = BookshelfApplication.myBookshelf.get(booktitle.getText());



        bookdata.add("Title: "+book.title);
        bookdata.add("Author: "+book.author);
        bookdata.add("Genre: "+book.genre);
        bookdata.add("Description: "+book.description);
        bookdata.add("Rating: "+ book.rating);
        bookdata.add("Publisher: "+book.publisher);
        bookdata.add("Release Date: "+book.release_date);

        bookinfo.setItems(bookdata);

        title.setText(book.title);
        author.setText(book.author);
        genre.setText(book.genre);
        description.setText(book.description);
        rating.setText(Double.toString(book.rating));
        publisher.setText(book.publisher);
        release_date.setText(book.release_date);
        bookLine = book.line;

    }

    @FXML
    public void OnModify(){

        if (booktitle.getText().equals("")){
            output.setText("Error, No Book to modify");
            return;
        }


        try {
            File file = new File("src/books.csv");
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(new CSVParserBuilder().withSeparator(',').build()).build();
            List<String[]> csvBody = csvReader.readAll();

            csvBody.get(bookLine)[0]=title.getText();
            csvBody.get(bookLine)[1]=author.getText();
            csvBody.get(bookLine)[2]=genre.getText();
            csvBody.get(bookLine)[3]=description.getText();
            csvBody.get(bookLine)[4]=rating.getText();
            csvBody.get(bookLine)[5]=publisher.getText();
            csvBody.get(bookLine)[6]=release_date.getText();
            csvReader.close();

            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();


            title.setText("");
            author.setText("");
            genre.setText("");
            description.setText("");
            rating.setText("");
            publisher.setText("");
            release_date.setText("");

            output.setText("Modification Success! Restart To See Changes");

        }
        catch (Exception e){
            output.setText("Error, Book Couldn't be modified");
        }

    }
    @FXML
    public void OnDelete(){
        if (booktitle.getText().equals("")){
            output.setText("Error, No Book to delete");
            return;
        }

        try {
            File file = new File("src/books.csv");
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(new CSVParserBuilder().withSeparator(',').build()).build();
            List<String[]> csvBody = csvReader.readAll();
            csvBody.remove(bookLine);
            csvReader.close();

            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();

            output.setText("File Successfully Deleted! Restart To See Changes");

        }catch (Exception e){
            output.setText("Error, Book Couldn't be deleted");
        }

    }

    @FXML
    public void OnAdd(){


        if (BookshelfApplication.myBookshelf.get(title.getText()) != null){
            output.setText("Error, Book Already Exists. Try A different title or Modifying it instead");
            return;
        }

        if (title.getText().equals("")){
            output.setText("Error, No Title Input.");
            return;
        }

        String[] temp = new String[7];
        temp[0]=title.getText();
        temp[1]=author.getText();
        temp[2]=genre.getText();
        temp[3]=description.getText();
        temp[4]=rating.getText();
        temp[5]=publisher.getText();
        temp[6]=release_date.getText();

        try {
            File file = new File("src/books.csv");
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(new CSVParserBuilder().withSeparator(',').build()).build();
            List<String[]> csvBody = csvReader.readAll();

            csvBody.add(temp);
            csvReader.close();


            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();

            output.setText("File Successfully Added! Restart To See Changes");

        }catch (Exception e){
            output.setText("File Unsuccessfully Added. Try Again");
        }
    }

}