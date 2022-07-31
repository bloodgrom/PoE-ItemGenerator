package com.poeitemgen.poeitemgenerator;

import com.poeitemgen.items.BodyArmor;
import com.poeitemgen.items.modifiers.Prefix;
import com.poeitemgen.items.utils.LimitedModifierSet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        armorTest.addPrefix(new Prefix(
                "Burning",
                15,
                2,
                "Normal",
                83,
                "Adds %s Fire Damage",
                "None",
                Arrays.asList(17,23)));

        armorTest.addPrefix(new Prefix(
                "Freezing",
                25,
                8,
                "Aisling",
                21,
                "Adds %s to %s Cold Damage",
                "None",
                Arrays.asList(45,51,72,79)));

        armorTest.addPrefix(new Prefix(
                "Freezing",
                19,
                8,
                "Aisling",
                211,
                "Adds %s to %s Cold Damage",
                "None",
                Arrays.asList(45,51,72,79)));

        armorTest.printItemText("Normal");

        System.out.println(armorTest.getAllPrefixTypes());
        System.out.println(armorTest.getAllPrefixGroupID());




        exit();
    }
}