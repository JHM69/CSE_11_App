package com.jhm69.cse11.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import android.provider.MediaStore;
import android.text.format.DateUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jhm69.cse11.Model.Notice;
import com.jhm69.cse11.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Jahangir
 */
public class FragmentAddPost extends DialogFragment implements View.OnClickListener{
    String[] example = {" 👉 8.30-9.55-English-Mukul sir(Room 721)👉 10-12.55-Group A(Hardware lab)-Tanvir sir 👉 10-12.55-Group B(software Lab)- Zulfiker sir, Arnisha mam Thanks❤","Zulfiker"};
    String[] image = {"https://jhm69.github.io/CSE-11-JnU/class_update.png"};
    private DatabaseReference mainDB;
    private TextView userNameTV, dateTimeTV;
    private Button addPhotoBtn, cancelBtn, postBtn;
    private EditText postET;
    private ImageView userImg, addImg;
    private DateUtils dateUtils;
    private Date selectedDate;
    private String imageEncoded;
    private String time, todaysDate;
    private String username, userimg;
    private FirebaseAuth firebaseAuth;
    private Uri filePath;
    FirebaseStorage storage;
    StorageReference NoticesReference;
    private final int PICK_IMAGE_REQUEST = 71;
    Bitmap bitmap;

    // private Income mIncome;
    // IncomeAdapter ad ;

    static FragmentAddPost newInstance() {
        FragmentAddPost  newFragmentAddPost = new FragmentAddPost();
        return newFragmentAddPost;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.add_post, container, false);
        userNameTV = (TextView)rootView.findViewById(R.id.userNameTV);
        dateTimeTV = rootView.findViewById(R.id.dateTimeTV);
        NoticesReference =  FirebaseStorage.getInstance().getReference().child("Notices");
        firebaseAuth = FirebaseAuth.getInstance();
        mainDB = FirebaseDatabase.getInstance().getReference();

        userImg = rootView.findViewById(R.id.userImg);
        addImg = rootView.findViewById(R.id.addImg);

        addPhotoBtn= rootView.findViewById(R.id.addPhotoBtn);
        cancelBtn = rootView.findViewById(R.id.cancelBtn);
        postBtn = rootView.findViewById(R.id.postBtn);

        postET = rootView.findViewById(R.id.postTextET);



        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        storage = FirebaseStorage.getInstance();

        time =  new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        todaysDate =  new SimpleDateFormat("dd-MM-YYYY", Locale.getDefault()).format(new Date());
        String userID = firebaseAuth.getCurrentUser().getUid();
        mainDB.child("1").child("Students").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                username = dataSnapshot.child("name").getValue().toString();
                userimg = dataSnapshot.child("img").getValue().toString();
                userNameTV.setText(username);


                userImg.setImageBitmap(decodeImage(userimg));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dateTimeTV.setText(time+", "+todaysDate);

        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        (getView().findViewById(R.id.cancelBtn)).setOnClickListener(this);
        (getView().findViewById(R.id.postBtn)).setOnClickListener(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addPhotoBtn) {

        }else if (view.getId() == R.id.postBtn) {
            if( imageEncoded == null){
                addPost();
            }else {
                addPostWithPhoto(imageEncoded);
            }
        }
        else if (view.getId() == R.id.cancelBtn) {
            dismiss();
        }
    }

    private void addPostWithPhoto(String imageEncoded) {
        Notice notice = new Notice(username,time+", "+todaysDate,postET.getText().toString(),imageEncoded);
        uploadToDatabase(notice);
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void addPost() {
        Notice notice = new Notice(username,time+", "+todaysDate,postET.getText().toString(),"00");
        uploadToDatabase(notice);

    }

    private void uploadToDatabase(Notice notice) {
        DatabaseReference classUpDB = mainDB.child( "0" ).child( "Notices" );
        classUpDB.push().setValue( notice ).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    dismiss();
                    Toast.makeText(getActivity(), "Posted :)", Toast.LENGTH_SHORT).show();
                }
            }
        } );
    }




        @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
           filePath = data.getData();
        {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(filePath));
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 1000, 600, false);
                imageEncoded = encodeImage(resizedBitmap, Bitmap.CompressFormat.JPEG, 90);
                addImg.setImageBitmap(resizedBitmap);
                addPhotoBtn.setText("Change the Image");
                addImg.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }



    public static String encodeImage(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

    }

    public static Bitmap decodeImage(String input) {
        byte[] decodeByte = Base64.decode(input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }

}
