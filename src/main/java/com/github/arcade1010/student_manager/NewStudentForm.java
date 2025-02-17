package com.github.arcade1010.student_manager;

import com.github.arcade1010.student_manager.student.Student;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.DateTimeException;
import java.time.LocalDate;


public class NewStudentForm {
    private  TextField nameInput;
    private static TextField emailInput;
    private static TextField dayInput;
    private static TextField monthInput;
    private static TextField yearInput;

    public Student display(){
        Stage window =  new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //blocks interaction with other window until this is closed.
        window.setTitle("New Student Form");
        window.setWidth(300);


        //Layout (labels and text fields)
        Label nameLabel = new Label();
        nameLabel.setText("Student Name:");
        nameInput = new TextField();

        Label emailLabel = new Label();
        emailLabel.setText("Student email:");
         emailInput = new TextField();

        Label DOBLabel = new Label();
        DOBLabel.setText("Student DOB(Numbers ex: 30, 5, 2003):");
         dayInput = new TextField("Day");
         monthInput = new TextField("Month");
         yearInput = new TextField("Year");

        Button submitButton = new Button("Submit");

        VBox layout = new VBox();
        layout.getChildren().addAll(nameLabel, nameInput, emailLabel, emailInput, DOBLabel, dayInput, monthInput, yearInput, submitButton);
        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);

        //arrays can be modified inside lambda (a local null student var couldn't)
        final Student[] studentHolder = new Student[1];
        submitButton.setOnAction(e -> {
            studentHolder[0] = handleSubmit();
            if(studentHolder[0] != null){
                window.close();
            }
        });
        window.showAndWait();
        return studentHolder[0];


    }
    //Make new student and return it, show alert if invalid input
    private  Student handleSubmit(){
        // Validate input fields first
        String name = nameInput.getText().trim();
        String email = emailInput.getText().trim();
        String dayText = dayInput.getText().trim();
        String monthText = monthInput.getText().trim();
        String yearText = yearInput.getText().trim();

        //Check fields are filled and email is valid
        if (name.isEmpty() || email.isEmpty() || dayText.isEmpty() || monthText.isEmpty() || yearText.isEmpty()) {
            showAlert("All fields must be filled out.", "Missing Information", "Please complete all fields before submitting.");
            return null;
        }
        if (!isValidEmail(email)){
            showAlert("Invalid email format.", "Invalid Input", "Please enter a valid email address");
            return null;
        }

        //Parse dates into LocalDate dob and return student.
        try{
            //Parse date into int
            int day = Integer.parseInt(dayText);
            int month = Integer.parseInt(monthText);
            int year = Integer.parseInt(yearText);

            LocalDate dob = LocalDate.of(year, month, day);

            return new Student(name, email, dob);

        } catch (NumberFormatException e){
            showAlert("Invalid number format.", "Invalid Input", "Please enter valid numbers.");
        } catch (DateTimeException e){
            showAlert("Invalid date", "Invalid Date", "Please enter a valid date.");
        }
        return null; //If validation fails
    }

    //Display alerts
    private void showAlert(String content, String header, String details){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input error");
        alert.setHeaderText(header);
        alert.setContentText(content + "\n" + details);
        alert.showAndWait();
    }

    //Validate email
    private boolean isValidEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }
}
