package com.poeitemgen.poeitemgenerator;

import com.poeitemgen.items.BodyArmor;
import com.poeitemgen.items.modifiers.Prefix;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Platform.exit;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        launch();

        BodyArmor armorTest = new BodyArmor("Random Armor 1");


        armorTest.printItemText();

        armorTest.addPrefix(new Prefix("Burning", 2, "Normal", 83));
        armorTest.addPrefix(new Prefix("Leeching", 8, "Aisling", 25));
        armorTest.addPrefix(new Prefix("Freezing", 5, "Normal", 156));

        armorTest.printItemText();

        armorTest.removePrefix();

        armorTest.printItemText();

        exit();
    }
}