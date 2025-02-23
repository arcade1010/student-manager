package com.github.arcade1010.student_manager;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateStudentForm {
    private Long id;
    private TextField idTextField;
    private String name;
    private TextField nameTextField;
    private String email;
    private TextField emailTextField;


    public Map<Long, List<String>> display(){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Update Student");
        stage.setWidth(300);

        //Layout
        Label idLabel = new Label(); //Id
        idLabel.setText("ID of student to update:");
        idTextField = new TextField();
        Label nameLabel = new Label(); //Name
        nameLabel.setText("Change name:");
        nameTextField = new TextField();
        Label emailLabel = new Label(); //Email
        emailLabel.setText("Change email:");
        emailTextField = new TextField();
        Button button = new Button("Submit"); //Submit

        VBox layout = new VBox();
        layout.getChildren().addAll(idLabel, idTextField, nameLabel, nameTextField, emailLabel, emailTextField, button);
        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);

        //Button to submit and return map to main GUI
        Map<Long, List<String>> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        //button stuff
        button.setOnAction(e -> {
            list.add(nameTextField.getText());
            list.add(emailTextField.getText());
            map.put(Long.parseLong(idTextField.getText()), list);
            stage.close();
        });

        stage.showAndWait();
        return map;

    }

}
