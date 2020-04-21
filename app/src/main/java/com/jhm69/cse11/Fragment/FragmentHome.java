package com.jhm69.cse11.Fragment;
import android.*;
import android.content.*;
import android.content.pm.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.*;

import android.util.Base64;
import android.view.*;
import android.view.View.*;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jhm69.cse11.Activity.AboutActivity;
import com.jhm69.cse11.Activity.ClassUpdateActivity;
import com.jhm69.cse11.Activity.StudentDetailsActivity;
import com.jhm69.cse11.Activity.StudentListActivity;
import com.jhm69.cse11.R;
import com.jhm69.cse11.Activity.RoutineActivity;
import com.jhm69.cse11.Activity.SlybsActivity;
import com.jhm69.cse11.Activity.TeacherListActivity;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class FragmentHome extends Fragment {
	
	CardView x;
	FirebaseAuth firebaseAuth;
	DatabaseReference mainDB;
	ImageView UserImg;
	CardView myprofile;
	TextView UserNme,UserEmail;
	String uid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.p_1, container, false);
		
		
    }
	/*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView iamge = (ImageView) view.findViewById(R.id.vector); */
		
		
	@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		requestStoragePermission();
		// Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
		UserImg = getActivity().findViewById(R.id.imguser);
		UserNme = getActivity().findViewById(R.id.nameuser);
		UserEmail = getActivity().findViewById(R.id.usermail);

		CardView student = (CardView) getActivity().findViewById(R.id.stud);
		CardView slybs = (CardView) getActivity().findViewById(R.id.slybs);
		CardView rout = (CardView) getActivity().findViewById(R.id.rout);
		CardView teacher =(CardView) getActivity().findViewById(R.id.teacher);
		CardView bus =(CardView) getActivity().findViewById(R.id.bus);
		CardView com =(CardView) getActivity().findViewById(R.id.com);
		myprofile = getActivity().findViewById(R.id.mypro);
		firebaseAuth = FirebaseAuth.getInstance();
        mainDB = FirebaseDatabase.getInstance().getReference();

		uid = firebaseAuth.getCurrentUser().getUid();

		try{
			loadData();
		}catch (Exception e){
			Toast.makeText(getActivity(), "Filed to load user Data", Toast.LENGTH_SHORT).show();
		}
		student.setOnClickListener(new OnClickListener(){
				@Override
				//On click function
				public void onClick(View view) {
					//Create the intent to start another activity
					Intent intent = new Intent(getActivity(), StudentListActivity.class);
					startActivity(intent);
				}
			});
		slybs.setOnClickListener(new OnClickListener(){
				@Override
				//On click function
				public void onClick(View view) {
					//Create the intent to start another activity
					Intent intent = new Intent(getActivity(), SlybsActivity.class);
					startActivity(intent);
				}
			});
		rout.setOnClickListener(new OnClickListener(){
				@Override
				//On click function
				public void onClick(View view) {
					//Create the intent to start another activity
					Intent intent = new Intent(getActivity(), RoutineActivity.class);
					startActivity(intent);
				}
			});
		teacher.setOnClickListener(new OnClickListener(){
				@Override
				//On click function
				public void onClick(View view) {
					//Create the intent to start another activity
					Intent intent = new Intent(getActivity(), TeacherListActivity.class);
					startActivity(intent);
				}
			});
		com.setOnClickListener(new OnClickListener(){
				@Override
				//On click function
				public void onClick(View view) {
					Intent intent = new Intent( getActivity(), ClassUpdateActivity.class );
					startActivity(intent);
				}
			});

		myprofile.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				Intent intent = new Intent( getActivity(), StudentDetailsActivity.class );
				intent.putExtra("1234",uid);
				startActivity(intent);
			}
		});






		bus.setOnClickListener(new OnClickListener(){
				@Override
				//On click function
				public void onClick(View view) {
					//Create the intent to start another activity
					
				    Intent intent = new Intent(getActivity(), AboutActivity.class);
					startActivity(intent);
				
					
				}
				
				private boolean appInstalledOrNot(String uri) {
					PackageManager pm = null;
					try {
						pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
						return true;
					} catch (PackageManager.NameNotFoundException e) {
					}

					return false;
				}
				
			});

		

		}
	

	
		
			
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		
		requestCallPermission();
		//x.setRadius(10);
		
	}

	private void requestStoragePermission(){



		if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), 
																android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {

        }

		int STORAGE_PERMISSION_CODE = 0;
		ActivityCompat.requestPermissions(getActivity(), 
										  new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
										  STORAGE_PERMISSION_CODE);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, 
										   @NonNull String[] permissions, 
										   @NonNull int[] grantResults) {


		int STORAGE_PERMISSION_CODE = 0;
		if (requestCode == STORAGE_PERMISSION_CODE)
		{
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
			{
			}
			else{
				Toast.makeText(getActivity(),
							   "Oops you just denied the permission", 
							   Toast.LENGTH_LONG).show();
			}


		}}
		
	private void requestCallPermission(){



		if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), 
																android.Manifest.permission.CALL_PHONE))
        {

        }

		int CALL_PHONE = 0;
		ActivityCompat.requestPermissions(getActivity(), 
										  new String[]{Manifest.permission.CALL_PHONE},
										  CALL_PHONE);
	}
	
	public void RequestPermissionsResult(int requestCode, 
										   @NonNull String[] permissions, 
										   @NonNull int[] grantResults) {


		int CALL_PHONE = 0;
		if (requestCode == CALL_PHONE)
		{
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
			{
			}
			else{
				Toast.makeText(getActivity(),
							   "Oops you just denied the permission", 
							   Toast.LENGTH_LONG).show();
			}


		}}

	public static Bitmap decodeImage(String input) {
		byte[] decodeByte = Base64.decode(input, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
	}

	public void loadData(){
		String userID = firebaseAuth.getCurrentUser().getUid();
		mainDB.child("1").child("Students").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				String name = dataSnapshot.child("name").getValue().toString();
				String present = dataSnapshot.child("present").getValue().toString();
				String img = dataSnapshot.child("img").getValue().toString();

				UserNme.setText(name);
				UserEmail.setText(present);
				try {
					UserImg.setImageBitmap(decodeImage(img));
				}catch (Exception e){
					Toast.makeText(getActivity(), "Filed Loading Image", Toast.LENGTH_SHORT).show();
				}

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});
	}


}

