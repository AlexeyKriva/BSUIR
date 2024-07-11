module org.project.paint3d {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.project.paint3d to javafx.fxml;
    exports org.project.paint3d;
}