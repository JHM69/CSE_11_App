package com.jhm69.cse11.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jhm69.cse11.Model.ClassUpdate;
import com.jhm69.cse11.R;

public class ClassUpdateActivity extends AppCompatActivity {
    private TextView start1, end1, sub1, sir1, room1,start2, end2, sub2, sir2, room2,start3, end3, sub3, sir3, room3,start4, end4, sub4, sir4, room4,start5, end5, sub5, sir5, room5;
    private Button dateBtn;
    private DatabaseReference mainDB;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_class_update );

        Toolbar toolbar = (Toolbar)findViewById(R.id.bar);
        setSupportActionBar(toolbar);
        iniatialization();
        updateDatafromDatabase();

        i = new Intent( this, UpdateClassEditActivity.class );

    }


    @Override
    protected void onResume() {
        updateDatafromDatabase();

        super.onResume();
    }

    @Override
    protected void onRestart() {
        updateDatafromDatabase();

        super.onRestart();
    }

    private void updateDatafromDatabase() {
        mainDB.child( "4" ).addChildEventListener( new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ClassUpdate newClassUpdate = dataSnapshot.getValue(ClassUpdate.class);
                Toast.makeText( ClassUpdateActivity.this, "Refreshed Data", Toast.LENGTH_SHORT ).show();
                UpateData(newClassUpdate);
                i.putExtra( "1" , newClassUpdate);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }

    private void UpateData(ClassUpdate newClassUpdate) {
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

        dateBtn.setText( newClassUpdate.getDate() );

    }

    private void iniatialization() {
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
        dateBtn = findViewById( R.id.btnDate );

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.class_update_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:

                //Intent intent = new Intent( this, UpdateClassEditActivity.class );
                if(sir1.getText().toString().equals("Updating")){
                    Toast.makeText(this, "Wait to refresh Data.", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity( i );
                }
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
