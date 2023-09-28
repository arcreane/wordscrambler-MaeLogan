module com.maelog.wordscrambled {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.maelog.wordscrambled to javafx.fxml;
    exports com.maelog.wordscrambled;
}