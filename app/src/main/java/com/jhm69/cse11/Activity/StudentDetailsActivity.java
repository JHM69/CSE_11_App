package com.jhm69.cse11.Activity;

import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.*;
import android.os.*;
import android.provider.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.*;

import android.util.Base64;
import android.view.*;
import android.widget.*;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jhm69.cse11.Adapters.StudentsRecycleAdapter;
import com.jhm69.cse11.Model.Student;
import com.jhm69.cse11.R;
import com.squareup.picasso.*;

import java.util.*;

public class StudentDetailsActivity extends AppCompatActivity {


	private ImageView imgf;
	private TextView Myname, Mynumber,Myblood, Myhome, Mypresent, Myfb, Mybirth, Mymess;
	private String name, number, blood, home, present, fblink, messlink,birth, imglink;
    DatabaseReference mainDB;
	LinearLayout fbx, callx, messx,pre_lo, town, birthc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_details);

		mainDB = FirebaseDatabase.getInstance().getReference();
		//name = deatil3.get(i).getName();
	
		imgf = (ImageView) findViewById(R.id.imgd);
		Myname = (TextView) findViewById(R.id.name);
		//Mynumber = (TextView) findViewById(R.id.nick);
		
		Myblood = (TextView) findViewById(R.id.blood_group );
		Mybirth= (TextView) findViewById(R.id.birth);
		Mypresent = (TextView) findViewById(R.id.present);
		Myhome= (TextView) findViewById(R.id.home);
		
		fbx = (LinearLayout) findViewById(R.id.fbx);
		callx = (LinearLayout) findViewById(R.id.callx);
		messx = (LinearLayout) findViewById(R.id.messx);
		pre_lo = (LinearLayout) findViewById(R.id.pre_lo);
		town = (LinearLayout) findViewById(R.id.town);
		birthc =(LinearLayout) findViewById(R.id.birthc);

		Intent intent = getIntent();
		String uid = intent.getStringExtra("1234" );
		updateData(uid);


		fbx.setOnClickListener(new View.OnClickListener() {  // <--- here
				@Override
				public void onClick(View v) {

					try {
						Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
						sharingIntent.setType("URL");
						sharingIntent.setData(Uri.parse("fb://facewebmodal/f?href="+fblink));	
						sharingIntent.setPackage("com.facebook.katana");
						startActivity(sharingIntent);
					} catch (ActivityNotFoundException e) {
						Uri uri = Uri.parse(fblink);
						Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(goToMarket);	
					}

				}
			});
		callx.setOnClickListener(new View.OnClickListener() {  // <--- here
				@Override
				public void onClick(View v) {				
					Intent callIntent = new Intent(Intent.ACTION_CALL);  
					callIntent.setData(Uri.parse("tel:"+number));
					startActivity(callIntent);  

				}
			});

		messx.setOnClickListener(new View.OnClickListener() {  // <--- here
				@Override
				public void onClick(View v) {				
					String url = messlink;
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}
			});
		pre_lo.setOnClickListener(new View.OnClickListener() {  // <--- here
				@Override
				public void onClick(View v) {				
					openMapView(present);
				}
			});
		town.setOnClickListener(new View.OnClickListener() {  // <--- here
				@Override
				public void onClick(View v) {				
					openMapView(home);
				}
			});
		birthc.setOnClickListener(new View.OnClickListener() {  // <--- here
				@Override
				public void onClick(View v) {			
				
					Intent intent = new Intent(Intent.ACTION_INSERT);
					intent.setType("vnd.android.cursor.item/event");

					Calendar cal = Calendar.getInstance();
					cal.clear();
					
					cal.set(2000,5,20,12,00);
					
					intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal); 
					intent.putExtra(CalendarContract.Events.CALENDAR_TIME_ZONE,cal);
					//intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime);
					intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

					//This is Information about Calender Event.
					intent.putExtra(CalendarContract.Events.TITLE, name+"'s Birthday");
					intent.putExtra(CalendarContract.Events.DESCRIPTION, "Add"+name+"'s Birthday");
					intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Jagannath University");
					intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");
					startActivity(intent);
				   
				}
			});



        //create default navigation drawer toggle


    }

	private void updateData(String uid) {
		mainDB.child("1").child("Students").child(uid).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				Student student = dataSnapshot.getValue(Student.class);
				name = student.getName();
				number = student.getNumber();
				blood = student.getBlood();
				home = student.getHome();
				present = student.getPresent();
				fblink = student.getFb();
				messlink = student.getMessenger();
				birth = student.getBirth();
				imglink = student.getImg();

				imgf.setImageBitmap(decodeImage(imglink));
				Myname.setText(name);
				Myblood.setText(blood);
				Mybirth.setText(birth);
				Mypresent.setText(present);
				Myhome.setText(home);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});

	}


	private void openMapView(String locationName){
		String map = "http://maps.google.com.bd/maps?q=" + locationName;
		try {
			Intent sharingIntent = new Intent(Intent.ACTION_VIEW);
			sharingIntent.setData(Uri.parse(map));	
			sharingIntent.setPackage("com.google.android.apps.maps");
			startActivity(sharingIntent);
		} catch (ActivityNotFoundException e) {
			Uri uri = Uri.parse(map);
			Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(goToMarket);	
		}


	}

	public static Bitmap decodeImage(String input) {
		byte[] decodeByte = Base64.decode(input, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
	}

}
