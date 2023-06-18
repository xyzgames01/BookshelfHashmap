package com.main.bookshelffinal;

public class Book {

    public String title, author, genre, description, publisher, release_date;

    public double rating;

    public int line;

    public Book(String[] data, int ln){
        this.title = data[0];
        this.author = data[1];
        this.genre = data[2];
        this.description = data[3];
        this.rating = Double.parseDouble(data[4]);
        this.publisher = data[5];
        this.release_date = data[6];
        line = ln;
    }

    public String toString(){

            return String.format("Title: %s Author: %s, Genre: %s, Description: %s Rating: %f Publisher: %s," +
                            " Release Date: %s ",title,author,genre,description,rating,publisher,release_date);
    }

}
