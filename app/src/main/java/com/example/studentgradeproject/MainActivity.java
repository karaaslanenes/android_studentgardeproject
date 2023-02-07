package com.example.studentgradeproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView studentNameText_view;
    private TextView gradeAverageText_view;
    private Button displayGradeButton;
    private Button nextStudentButton;
    private Button previousStudentButton;
    private Button gradeDetailsButton;

    private int  currentIndex=0;
    public static final String TAG="Grade Project";
    public static final String KEY_INDEX="index";

    Grade student1=new Grade(1,"Graham","Bill",69,70,98,80,90);
    Grade student2=new Grade(2,"Sanchez","Jim",88,72,90,83,93);
    Grade student3=new Grade(3,"White","Peter",85,80,45,93,70);
    Grade student4=new Grade (4,"Phelp","David",70,60,60,90,70);
    Grade student5=new Grade(5,"Lewis","Sheila",50,76,87,59,72);
    Grade student6=new Grade(6,"James","Thomas",89,99,97,98,99);
    Grade [] all_students=new Grade[]{student1,student2,student3,student4,student5,student6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Grade student1=new Grade(1,"Graham","Bill",69,70,98,80,90);
//        Grade student2=new Grade(2,"Sanchez","Jim",88,72,90,83,93);
//        Grade student3=new Grade(3,"White","Peter",85,80,45,93,70);
//        Grade student4=new Grade (4,"Phelp","David",70,60,60,90,70);
//        Grade student5=new Grade(5,"Lewis","Sheila",50,76,87,59,72);
//        Grade student6=new Grade(6,"James","Thomas",89,99,97,98,99);
//        Grade [] all_students=new Grade[]{student1,student2,student3,student4,student5,student6};

        if (savedInstanceState!=null)
        {
            currentIndex=savedInstanceState.getInt(KEY_INDEX);
        }

        studentNameText_view=(TextView) findViewById(R.id.studentname_text_view);
        studentNameText_view.setText("Student :"+all_students[currentIndex].getStudent_lname()+" "+all_students[currentIndex].getStudent_fname());

        displayGradeButton=(Button) findViewById(R.id.display_button);
        displayGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gradeAverageText_view=(TextView) findViewById(R.id.gradeaverage_text_view);
                gradeAverageText_view.setText("Grade Average is :"+all_students[currentIndex].Calculate_GradeAverage()+"%");
                Toast.makeText(MainActivity.this, "Pass  with grade of :" + all_students[currentIndex].Calculate_GradeAverage() + "%", Toast.LENGTH_SHORT).show();

            }
        });

        nextStudentButton=(Button) findViewById(R.id.next_button);
        nextStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex=(currentIndex+1)%all_students.length;
                studentNameText_view.setText("Student :"+all_students[currentIndex].getStudent_lname()+" "+all_students[currentIndex].getStudent_fname());
                gradeAverageText_view=(TextView) findViewById(R.id.gradeaverage_text_view);
                gradeAverageText_view.setText("");

            }
        });

        previousStudentButton=(Button) findViewById(R.id.previous_button);
        previousStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex=(currentIndex+(all_students.length-1))%all_students.length;
                studentNameText_view.setText("Student :"+all_students[currentIndex].getStudent_lname()+" "+all_students[currentIndex].getStudent_fname());
                gradeAverageText_view=(TextView) findViewById(R.id.gradeaverage_text_view);
                gradeAverageText_view.setText("");
            }
        });


        gradeDetailsButton=(Button) findViewById(R.id.gradedetails_button);
        gradeDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int  studentId=all_students[currentIndex].getStudent_id();
                String studentFirstname=all_students[currentIndex].getStudent_fname();
                String studentLastname=all_students[currentIndex].getStudent_lname();
                int gradeAssignment1=all_students[currentIndex].getS_grade_Assignment1();
                int gradeAssignment2=all_students[currentIndex].getS_grade_Assignment2();
                int gradeAssignment3=all_students[currentIndex].getS_grade_Assignment3();
                int gradeMidterm=all_students[currentIndex].getS_grade_Mid_Term();
                int gradeFinal=all_students[currentIndex].getS_grade_Final_Term();


                Intent intent=StudentGradeActivity.newIntent(MainActivity.this,studentId,studentFirstname,studentLastname,gradeAssignment1,
                        gradeAssignment2,gradeAssignment3,gradeMidterm,gradeFinal);
                //startActivity(intent);
                startActivityIntent.launch(intent);
            }
        });

    }
 ActivityResultLauncher <Intent> startActivityIntent=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
         new ActivityResultCallback<ActivityResult>() {
             @Override
             public void onActivityResult(ActivityResult result) {
                 if (result.getResultCode() != Activity.RESULT_OK) {
                     return;
                 } else {
                     Grade gradeUpdateInfo = StudentGradeActivity.sendMessageGradeUpdateResult(result.getData());
                     studentNameText_view=(TextView) findViewById(R.id.studentname_text_view);
                     studentNameText_view.setText("Updated Student :"+gradeUpdateInfo.getStudent_lname()+" "+gradeUpdateInfo.getStudent_fname());
                     gradeAverageText_view=(TextView) findViewById(R.id.gradeaverage_text_view);
                     gradeAverageText_view.setText(" Updated Grade Average is :"+gradeUpdateInfo.Calculate_GradeAverage()+"%");

                     all_students[currentIndex].setStudent_id(gradeUpdateInfo.getStudent_id());
                     all_students[currentIndex].setStudent_fname(gradeUpdateInfo.getStudent_fname());
                     all_students[currentIndex].setStudent_lname(gradeUpdateInfo.getStudent_lname());
                     all_students[currentIndex].setS_grade_Assignment1(gradeUpdateInfo.getS_grade_Assignment1());
                     all_students[currentIndex].setS_grade_Assignment2(gradeUpdateInfo.getS_grade_Assignment2());
                     all_students[currentIndex].setS_grade_Assignment3(gradeUpdateInfo.getS_grade_Assignment3());
                     all_students[currentIndex].setS_grade_Mid_Term(gradeUpdateInfo.getS_grade_Mid_Term());
                     all_students[currentIndex].setS_grade_Final_Term(gradeUpdateInfo.getS_grade_Final_Term());
                     Toast.makeText(MainActivity.this, "Updated student :"+"Pass  with grade of :" + all_students[currentIndex].Calculate_GradeAverage() + "%", Toast.LENGTH_SHORT).show();



                 }
             }
         });




    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG,"onResume() called");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG,"onSaveInstanceState() called");
        savedInstanceState.putInt(KEY_INDEX,currentIndex);
    }


    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
}