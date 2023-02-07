package com.example.studentgradeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentGradeActivity extends AppCompatActivity {
    private Button studentGradeUpdateButton;
    private EditText studentIdEditText;
    private EditText studentFirstNameEditText;
    private EditText studentLastNameEditText;
    private EditText gradeAssignment1EditText;
    private EditText gradeAssignment2EditText;
    private EditText gradeAssignment3EditText;
    private EditText gradeMidtermEditText;
    private EditText gradeFinalEditText;

    private static final String EXTRA_STUDENT_ID="com.example.studentgradeproject.student_id";
    private static final String EXTRA_STUDENT_FIRST_NAME="com.example.studentgradeproject.student_first_name";
    private static final String EXTRA_STUDENT_LAST_NAME="com.example.studentgradeproject.student_last_name";
    private static final String EXTRA_GRADE_ASSIGNMENT1="com.example.studentgradeproject.grade_assignment1";
    private static final String EXTRA_GRADE_ASSIGNMENT2="com.example.studentgradeproject.grade_assignment2";
    private static final String EXTRA_GRADE_ASSIGNMENT3="com.example.studentgradeproject.grade_assignment3";
    private static final String EXTRA_GRADE_MIDTERM="com.example.studentgradeproject.grade_midterm";
    private static final String EXTRA_GRADE_FINAL="com.example.studentgradeproject.grade_final";
//    private int  currentIndex=0;
//    public static final String TAG="Grade Project";
//    public static final String KEY_INDEX="index";



    private int studentIdRetrieve;
    private String studentFirstNameRetrieve;
    private String studentLastNameRetrieve;
    private int gradeAssignment1Retrieve;
    private int gradeAssignment2Retrieve;
    private int gradeAssignment3Retrieve;
    private int gradeMidtermRetrieve;
    private int gradeFinalRetrieve;

    public static Intent newIntent(Context packageContext, int student_id, String student_first_name,String student_last_name,int grade_assignment1,int grade_assignment2,int grade_assignment3,int grade_midterm,int  grade_final)
    {
        Intent intent=new Intent(packageContext,StudentGradeActivity.class);
       intent.putExtra(EXTRA_STUDENT_ID,student_id);
       intent.putExtra(EXTRA_STUDENT_FIRST_NAME,student_first_name);
       intent.putExtra(EXTRA_STUDENT_LAST_NAME,student_last_name);
       intent.putExtra(EXTRA_GRADE_ASSIGNMENT1,grade_assignment1);
        intent.putExtra(EXTRA_GRADE_ASSIGNMENT2,grade_assignment2);
        intent.putExtra(EXTRA_GRADE_ASSIGNMENT3,grade_assignment3);
        intent.putExtra(EXTRA_GRADE_MIDTERM,grade_midterm);
        intent.putExtra(EXTRA_GRADE_FINAL,grade_final);
        return intent;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_grade);

//        if (savedInstanceState!=null)
//        {
//            currentIndex=savedInstanceState.getInt(KEY_INDEX);
//        }
        studentIdRetrieve=getIntent().getIntExtra(EXTRA_STUDENT_ID,0);
        studentFirstNameRetrieve=getIntent().getStringExtra(EXTRA_STUDENT_FIRST_NAME);
        studentLastNameRetrieve=getIntent().getStringExtra(EXTRA_STUDENT_LAST_NAME);
        gradeAssignment1Retrieve=getIntent().getIntExtra(EXTRA_GRADE_ASSIGNMENT1,0);
        gradeAssignment2Retrieve=getIntent().getIntExtra(EXTRA_GRADE_ASSIGNMENT2,0);
        gradeAssignment3Retrieve=getIntent().getIntExtra(EXTRA_GRADE_ASSIGNMENT3,0);
        gradeMidtermRetrieve=getIntent().getIntExtra(EXTRA_GRADE_MIDTERM,0);
        gradeFinalRetrieve=getIntent().getIntExtra(EXTRA_GRADE_FINAL,0);

         studentIdEditText=(EditText) findViewById(R.id.student_id_edit_text);
         studentIdEditText.setText(studentIdRetrieve+"");

        studentFirstNameEditText=(EditText) findViewById(R.id.student_fname_edit_text);
        studentFirstNameEditText.setText(studentFirstNameRetrieve);

        studentLastNameEditText=(EditText) findViewById(R.id.student_lname_edit_text);
        studentLastNameEditText.setText(studentLastNameRetrieve);

        gradeAssignment1EditText=(EditText) findViewById(R.id.s_grade_Assignment1_edit_text);
        gradeAssignment1EditText.setText(gradeAssignment1Retrieve+"");

        gradeAssignment2EditText=(EditText) findViewById(R.id.s_grade_Assignment2_edit_text);
        gradeAssignment2EditText.setText(gradeAssignment2Retrieve+"");

        gradeAssignment3EditText=(EditText) findViewById(R.id.s_grade_Assignment3_edit_text);
        gradeAssignment3EditText.setText(gradeAssignment3Retrieve+"");

        gradeMidtermEditText=(EditText) findViewById(R.id.s_grade_Mid_Term_edit_text);
        gradeMidtermEditText.setText(gradeMidtermRetrieve+"");

        gradeFinalEditText=(EditText) findViewById(R.id.s_grade_Final_Term_edit_text);
        gradeFinalEditText.setText(gradeFinalRetrieve+"");

        studentGradeUpdateButton=(Button) findViewById(R.id.student_grade_update_button);
        studentGradeUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setGradeUpdateResult(Integer.parseInt(studentIdEditText.getText().toString()),
                        studentFirstNameEditText.getText().toString(),
                        studentLastNameEditText.getText().toString(),
                        Integer.parseInt(gradeAssignment1EditText.getText().toString()),
                        Integer.parseInt(gradeAssignment2EditText.getText().toString()),
                        Integer.parseInt(gradeAssignment3EditText.getText().toString()),
                        Integer.parseInt(gradeMidtermEditText.getText().toString()),
                        Integer.parseInt(gradeFinalEditText.getText().toString()));

            }
        });

    }
    private void setGradeUpdateResult(int student_id, String student_first_name,String student_last_name,int grade_assignment1,int grade_assignment2,int grade_assignment3,int grade_midterm,int  grade_final)
    {
        Intent dataIntent=new Intent();
        dataIntent.putExtra(EXTRA_STUDENT_ID,student_id);
        dataIntent.putExtra(EXTRA_STUDENT_FIRST_NAME,student_first_name);
        dataIntent.putExtra(EXTRA_STUDENT_LAST_NAME,student_last_name);
        dataIntent.putExtra(EXTRA_GRADE_ASSIGNMENT1,grade_assignment1);
        dataIntent.putExtra(EXTRA_GRADE_ASSIGNMENT2,grade_assignment2);
        dataIntent.putExtra(EXTRA_GRADE_ASSIGNMENT3,grade_assignment3);
        dataIntent.putExtra(EXTRA_GRADE_MIDTERM,grade_midterm);
        dataIntent.putExtra(EXTRA_GRADE_FINAL,grade_final);
        setResult(RESULT_OK,dataIntent);
    }
    public static Grade sendMessageGradeUpdateResult(Intent resultIntent){

        Grade gradeUpdateInfo=new Grade();
        gradeUpdateInfo.setStudent_id(resultIntent.getIntExtra(EXTRA_STUDENT_ID,0));
        gradeUpdateInfo.setStudent_fname(resultIntent.getStringExtra(EXTRA_STUDENT_FIRST_NAME));
        gradeUpdateInfo.setStudent_lname(resultIntent.getStringExtra(EXTRA_STUDENT_LAST_NAME));
        gradeUpdateInfo.setS_grade_Assignment1(resultIntent.getIntExtra(EXTRA_GRADE_ASSIGNMENT1,0));
        gradeUpdateInfo.setS_grade_Assignment2(resultIntent.getIntExtra(EXTRA_GRADE_ASSIGNMENT2,0));
        gradeUpdateInfo.setS_grade_Assignment3(resultIntent.getIntExtra(EXTRA_GRADE_ASSIGNMENT3,0));
        gradeUpdateInfo.setS_grade_Mid_Term(resultIntent.getIntExtra(EXTRA_GRADE_MIDTERM,0));
        gradeUpdateInfo.setS_grade_Final_Term(resultIntent.getIntExtra(EXTRA_GRADE_FINAL,0));
        return  gradeUpdateInfo;


    }
//    @Override
//    public void onStart()
//    {
//        super.onStart();
//        Log.d(TAG,"onStart() called");
//    }
//    @Override
//    public void onPause()
//    {
//        super.onPause();
//        Log.d(TAG,"onPause() called");
//    }
//    @Override
//    public void onResume()
//    {
//        super.onResume();
//        Log.d(TAG,"onResume() called");
//    }
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState)
//    {
//        super.onSaveInstanceState(savedInstanceState);
//        Log.d(TAG,"onSaveInstanceState() called");
//        savedInstanceState.putInt(KEY_INDEX,currentIndex);
//    }
//
//    @Override
//    public void onStop()
//    {
//        super.onStop();
//        Log.d(TAG,"onStop() called");
//    }
//    @Override
//    public void onDestroy()
//    {
//        super.onDestroy();
//        Log.d(TAG,"onDestroy() called");
//    }
}