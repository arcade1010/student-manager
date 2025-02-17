package com.github.arcade1010.student_manager;

import com.github.arcade1010.student_manager.student.Student;
import com.github.arcade1010.student_manager.student.StudentController;
import com.github.arcade1010.student_manager.student.StudentService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class StudentManagerGUI extends Application {
    //start is invoked
    public static void main(String[] args){launch();}

    //Start spring boot api
    private ConfigurableApplicationContext springContext;
    @Override
    public void init(){
        springContext = SpringApplication.run(StudentManagerApplication.class);
    }

    //Stop spring api
    @Override
    public void stop(){
        springContext.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Student Manager");

        //Basic layout
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
        viewButton.setOnAction(event -> resultLabel.setText("Students: " + getAllStudents() ));


        //Add student
        addButton.setOnAction(event -> resultLabel.setText(addStudent()));


        //Remove student




        //Update student

    }

    //getAllStudents
    public String getAllStudents(){
        //get StudentController bean from spring (springContext)
        StudentController sc = springContext.getBean(StudentController.class);
        List<Student> studentsList = sc.getStudents();

        //Convert List to String
        StringBuilder students = new StringBuilder();
        for(Student s : studentsList){
            students.append(s.getName()).append(", ");
        }
        return students.toString();
    }

    //addStudent
    //Currently database does not persist between runs. (only students in config appear at launch)
    public String addStudent(){
        NewStudentForm newStudentForm = new NewStudentForm();
        StudentController sc = springContext.getBean(StudentController.class);
        sc.registerNewStudent(newStudentForm.display());
        return "Student was added successfully.";
    }
}
