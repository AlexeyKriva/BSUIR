module org.project.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.project.demo to javafx.fxml;
    exports org.project.demo;
}