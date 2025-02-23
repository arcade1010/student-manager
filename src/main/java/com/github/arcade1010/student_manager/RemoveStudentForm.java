package com.github.arcade1010.student_manager;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RemoveStudentForm {
    private Long id;
    private static TextField idTextField;

    public long display(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //blocks interaction with other windows
        window.setTitle("Remove a student");
        window.setWidth(300);

        //Layout
        Label label = new Label();
        label.setText("Student ID");
        idTextField = new TextField();
        Button button = new Button("Submit");

        VBox layout = new VBox();
        layout.getChildren().addAll(label, idTextField, button);
        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);

        //Java lambda expressions cant alter a simple variable but can alter arrays.
        final Long[] validIdHolder = new Long[1];
        button.setOnAction(e -> {
            validIdHolder[0] = handleSubmit();
            if(validIdHolder[0] != null){
                window.close();
            }
        });

        window.showAndWait();
        return validIdHolder[0];
    }

    //handleSubmit - validate id input to text field as a Long and ensure student with that id exists
    public Long handleSubmit() {
        String idString = idTextField.getText().trim();
        if (idString.isEmpty()){
            showAlert("Field must be filled out", "Missing information", "Please enter a valid student id number");
        }

        try{
            id = Long.parseLong(idString);
            return id;
        } catch (NumberFormatException e){
            showAlert("Number Format Exception", "Invalid input", "Enter a valid student id number");
        }
        return null; //if validation fails (shouldn't ever get to this)
    }

    //showAlert
    private void showAlert(String content, String header, String details){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input error");
        alert.setHeaderText(header);
        alert.setContentText(content + "\n" + details);
        alert.showAndWait();

    }

}
