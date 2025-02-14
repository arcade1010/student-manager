package com.github.arcade1010.student_manager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class StudentManagerGUI extends Application {
    //start is invoked
    public static void main(String[] args){launch();}

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Student Manager");

        Label l1 = new Label("What would you like to do?");
        Button viewButton = new Button("View students");
        Button addButton = new Button("Add a student");
        Button removeButton = new Button("Remove a student");
        Button updateButton = new Button("Update a student");
        Label resultLabel = new Label("Result Label"); //Will be updated according to button pressed

        VBox primaryLayout = new VBox();
        primaryLayout.getChildren().addAll(l1, viewButton, addButton, removeButton, updateButton, resultLabel);

        Scene scene = new Scene(primaryLayout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();



        //View students



        //Add student



        //Remove student




        //Update student

    }
}
