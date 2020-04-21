package com.jhm69.cse11.Activity;

import android.os.*;

import androidx.appcompat.app.*;
import androidx.appcompat.widget.*;

import android.widget.*;

import com.jhm69.cse11.R;

import androidx.appcompat.widget.Toolbar;
//import com.github.barteksc.pdfviewer.*;

public class RoutineActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine);
		
		Toast.makeText(getApplicationContext(), "pinch to zoom the routine.",Toast.LENGTH_SHORT).show();  
		
		//PDFView pdfView=findViewById(R.id.pdfv);
       // pdfView.fromAsset("routine.pdf").load();
    }



}
