package com.jhm69.cse11.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jhm69.cse11.Model.Notice;
import com.jhm69.cse11.Model.Student;
import com.jhm69.cse11.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

public class SingUpActivity extends AppCompatActivity {
    private TextView loginTV;
    private EditText numberET, emailET,passET,confirmPassET,nameET,birthET,bloodET,presentET,homeET,fbET,messengerET,nickET;
    private Button singUp;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private ImageView profileImg;
    private CardView plusImg;
    private DatabaseReference mainDB;
    private final int PICK_IMAGE_REQUEST = 71;
    private Uri filePath;
    private String defaultImg = "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\n";
    Intent CropIntent ;
    ProgressDialog progressDialog;
    FirebaseStorage storage;
    StorageReference userImage;
    private Bitmap bitmap;
    private String imageEncoded="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        initialization();
        choseImage();
        onClick();
    }

    private void choseImage() {
        plusImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("return-data", true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("return-data", true);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null )
             filePath = data.getData();
             {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(SingUpActivity.this.getContentResolver(),filePath);
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(filePath));
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 500, 500, false);
                imageEncoded = encodeImage(resizedBitmap, Bitmap.CompressFormat.PNG, 80);
                profileImg.setImageBitmap(resizedBitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }


    private void onClick() {
        loginTV .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SingUpActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String pass = passET.getText().toString();
                String name = nameET.getText().toString();
                String birth = birthET.getText().toString();
                String blood = bloodET.getText().toString();
                String home = homeET.getText().toString();
                String present = presentET.getText().toString();
                String fb = fbET.getText().toString();
                String messener = messengerET.getText().toString();
                String nick = nickET.getText().toString();
                String confrimPass = confirmPassET.getText().toString();
                String number = numberET.getText().toString();
                if(number.equals("") || number.length() != 11){
                    Toast.makeText(SingUpActivity.this, "11 characters Must!", Toast.LENGTH_SHORT).show();
                }else if(email.equals("")){
                    Toast.makeText(SingUpActivity.this, "Invalid Email Address!", Toast.LENGTH_SHORT).show();
                }
                else if(pass.equals("") || pass.length()<6){
                    Toast.makeText(SingUpActivity.this, "6 characters must", Toast.LENGTH_SHORT).show();
                }else if (!pass.matches(confrimPass)){
                    Toast.makeText(SingUpActivity.this, "Password doesn't matched!", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog = ProgressDialog
                            .show(SingUpActivity.this,
                                    "Singing UP...",
                                    "");
                    if(filePath==null){
                        CreateNewStudent(email,birth,blood,fb,home,defaultImg,messener,name,nick,number,present,pass);
                    }else{
                        CreateNewStudent(email,birth,blood,fb,home,imageEncoded,messener,name,nick,number,present,pass);
                    }
                }
            }
        });

    }

    private void CreateNewStudent(String email,final String birth,final String blood,final String fb,final String home,final String img,final String messener,final String name,final String nick,final String number,final String present, String pass) {

        firebaseAuth.createUserWithEmailAndPassword(email ,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Student student = new Student(birth,blood,fb,home,img,messener,name,nick,number,present, firebaseAuth.getCurrentUser().getUid());
                    DatabaseReference userDB = mainDB.child("1").child("Students").child(firebaseAuth.getCurrentUser().getUid());
                    userDB.setValue(student);
                    Intent intent = new Intent(SingUpActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    progressDialog.dismiss();
                    finish();
                    Toast.makeText(SingUpActivity.this, "Account Created successfully", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(SingUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void initialization() {
        loginTV = findViewById(R.id.singup);
        emailET = findViewById(R.id.userEmailET);
        nameET = findViewById(R.id.userName);
        passET = findViewById(R.id.passwordET);
        confirmPassET = findViewById(R.id.ConfrimPasswords);
        bloodET =findViewById(R.id.bloodET);
        birthET = findViewById(R.id.birthET);
        fbET = findViewById(R.id.fbET);
        messengerET = findViewById(R.id.messengerET);
        presentET = findViewById(R.id.presentET);
        homeET = findViewById(R.id.homeET);
        nickET = findViewById(R.id.nickTV);
        profileImg = findViewById(R.id.profile_image);
        numberET = findViewById(R.id.numberET);
        singUp = findViewById(R.id.loginBT);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar4);
        mainDB  = FirebaseDatabase.getInstance().getReference();
        userImage = FirebaseStorage.getInstance().getReference().child("User Images");

        plusImg = findViewById(R.id.addPlus);

    }
    public static String encodeImage(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

    }

}