package com.jhm69.cse11.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jhm69.cse11.Model.ClassUpdate;
import com.jhm69.cse11.R;
import com.jhm69.cse11.utils.DialogManager;
import com.jhm69.cse11.utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdateClassEditActivity extends AppCompatActivity {
    AutoCompleteTextView start1, end1, sub1, sir1, room1,start2, end2, sub2, sir2, room2,start3, end3, sub3, sir3, room3,start4, end4, sub4, sir4, room4,start5, end5, sub5, sir5, room5;
    TextView crCode;
    String[] sir = {"Zulfiker Sir","Tanvir Sir","Ayub Ali sir","Mukul sir","Ujjal Kumar Sir","Tanveer sir","Momin sir" ,"Arnisha Mam"};
    String[] room = {"721", "VC", "Shanto Chottor", "601","Software lab 1","Hardware Lab" };
    String[] sub = {"Physics","History", "Electrical Circuit", "C Programming","English","Software Lab", "Hardware Lab"};
    private DatabaseReference mainDB;
    Button dateBtn;
    private Date selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update_class_edit );

        Toolbar toolbar = (Toolbar)findViewById(R.id.bar);
        setSupportActionBar(toolbar);

        initialization();
        setupAdapter();

        Intent intent = getIntent();
        ClassUpdate classUpdate =(ClassUpdate)intent.getSerializableExtra( "1" );
        setTextData(classUpdate);
        selectedDate = new Date();
        updateDate();
        dateBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        } );

    }


    private void setTextData(ClassUpdate newClassUpdate) {
        start1.setText( newClassUpdate.getStart1() );
        end1.setText( newClassUpdate.getEnd1() );
        sub1.setText( newClassUpdate.getSub1() );
        sir1.setText( newClassUpdate.getSir1() );
        room1.setText( newClassUpdate.getRoom1() );

        start2.setText( newClassUpdate.getStart2() );
        end2.setText( newClassUpdate.getEnd2() );
        sub2.setText( newClassUpdate.getSub2() );
        sir2.setText( newClassUpdate.getSir2() );
        room2.setText( newClassUpdate.getRoom2() );


        start3.setText( newClassUpdate.getStart3() );
        end3.setText( newClassUpdate.getEnd3() );
        sub3.setText( newClassUpdate.getSub3() );
        sir3.setText( newClassUpdate.getSir3() );
        room3.setText( newClassUpdate.getRoom3() );


        start4.setText( newClassUpdate.getStart4() );
        end4.setText( newClassUpdate.getEnd4() );
        sub4.setText( newClassUpdate.getSub4() );
        sir4.setText( newClassUpdate.getSir4() );
        room4.setText( newClassUpdate.getRoom4() );

        start5.setText( newClassUpdate.getStart5() );
        end5.setText( newClassUpdate.getEnd5() );
        sub5.setText( newClassUpdate.getSub5() );
        sir5.setText( newClassUpdate.getSir5() );
        room5.setText( newClassUpdate.getRoom5() );



    }

    private void setupAdapter() {

        ArrayAdapter<String> sirAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, sir);
        ArrayAdapter<String> subAdapter  = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, sub);
        ArrayAdapter<String> roomAdapter  = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, room);


        sub1.setAdapter( subAdapter );
        sub1.setThreshold( 1 );
        sir1.setAdapter( sirAdapter );
        sir1.setThreshold( 1 );
        room1.setAdapter( roomAdapter );
        room1.setThreshold( 1 );


        sub2.setAdapter( subAdapter );
        sir2.setAdapter( sirAdapter );
        room2.setAdapter( roomAdapter );

        sub3.setAdapter( subAdapter );
        sir3.setAdapter( sirAdapter );
        room3.setAdapter( roomAdapter );

        sub4.setAdapter( subAdapter );
        sir4.setAdapter( sirAdapter );
        room4.setAdapter( roomAdapter );

        sub5.setAdapter( subAdapter );
        sir5.setAdapter( sirAdapter );
        room5.setAdapter( roomAdapter );


    }

    private void initialization() {
        start1 = findViewById( R.id.start1 );
        end1 = findViewById( R.id.end1 );
        sub1 = findViewById( R.id.sub1 );
        sir1 = findViewById( R.id.sir1 );
        room1 = findViewById( R.id.room1 );

        start2 = findViewById( R.id.start2 );
        end2= findViewById( R.id.end2 );
        sub2 = findViewById( R.id.sub2 );
        sir2 = findViewById( R.id.sir2 );
        room2 = findViewById( R.id.room2 );

        start3= findViewById( R.id.start3);
        end3 = findViewById( R.id.end3 );
        sub3 = findViewById( R.id.sub3);
        sir3 = findViewById( R.id.sir3 );
        room3 = findViewById( R.id.room3);

        start4 = findViewById( R.id.start4 );
        end4 = findViewById( R.id.end4 );
        sub4 = findViewById( R.id.sub4 );
        sir4 = findViewById( R.id.sir4 );
        room4 = findViewById( R.id.room4 );

        start5 = findViewById( R.id.start5 );
        end5 = findViewById( R.id.end5 );
        sub5 = findViewById( R.id.sub5 );
        sir5 = findViewById( R.id.sir5 );
        room5 = findViewById( R.id.room5 );

        mainDB = FirebaseDatabase.getInstance().getReference();
        dateBtn = findViewById( R.id.dateBtn );
        crCode = findViewById( R.id.crCode );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                if (crCode.getText().toString().equals("1127")){
                    updateClassUpdate();
                }else{
                    Toast.makeText(this, "Unsuccessful! Wrong CR Code.", Toast.LENGTH_SHORT).show();
                }
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void updateClassUpdate() {

        String SelectedDate = Utils.formatDateToString(selectedDate, "dd-MM-YYYY");

        Map<String,Object> newUpdate =new HashMap<>();

        newUpdate.put( "date",SelectedDate );

        newUpdate.put( "start1" , start1.getText().toString());
        newUpdate.put( "end1" , end1.getText().toString());
        newUpdate.put( "sub1" , sub1.getText().toString());
        newUpdate.put( "sir1" , sir1.getText().toString());
        newUpdate.put( "room1" , room1.getText().toString());


        newUpdate.put( "start2" , start2.getText().toString());
        newUpdate.put( "end2" , end2.getText().toString());
        newUpdate.put( "sub2" , sub2.getText().toString());
        newUpdate.put( "sir2" , sir2.getText().toString());
        newUpdate.put( "room2" , room2.getText().toString());


        newUpdate.put( "start3" , start3.getText().toString());
        newUpdate.put( "end3" , end3.getText().toString());
        newUpdate.put( "sub3" , sub3.getText().toString());
        newUpdate.put( "sir3" , sir3.getText().toString());
        newUpdate.put( "room3" , room3.getText().toString());


        newUpdate.put( "start4" , start4.getText().toString());
        newUpdate.put( "end4" , end4.getText().toString());
        newUpdate.put( "sub4" , sub4.getText().toString());
        newUpdate.put( "sir4" , sir4.getText().toString());
        newUpdate.put( "room4" , room4.getText().toString());


        newUpdate.put( "start5" , start5.getText().toString());
        newUpdate.put( "end5" , end5.getText().toString());
        newUpdate.put( "sub5" , sub5.getText().toString());
        newUpdate.put( "sir5" , sir5.getText().toString());
        newUpdate.put( "room5" , room5.getText().toString());
        DatabaseReference classUpDB = mainDB.child( "4" ).child( "ClassUpdate" );
        classUpDB.setValue( newUpdate ).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText( UpdateClassEditActivity.this, "Successfully Updated. Thank you CR.", Toast.LENGTH_SHORT ).show();
                    finish();
                }else{
                    Toast.makeText( UpdateClassEditActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT ).show();
                }
            }
        } );

    }

    private void showDateDialog() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);
        DialogManager.getInstance().showDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(year, month, day);
                selectedDate = calendar.getTime();
                updateDate();
            }
        }, calendar);
    }
    private void updateDate() {
        dateBtn.setText( Utils.formatDateToString(selectedDate, "dd/MM/YYYY"));
    }
}
