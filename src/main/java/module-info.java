module com.poeitemgen.poeitemgenerator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.poeitemgen.poeitemgenerator to javafx.fxml;
    exports com.poeitemgen.poeitemgenerator;
}