module com.example.bookshelffinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.main.bookshelffinal to javafx.fxml;
    exports com.main.bookshelffinal;
}