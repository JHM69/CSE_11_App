package com.jhm69.cse11.Adapters;


import android.annotation.SuppressLint;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.*;
import android.util.Base64;
import android.view.*;
import android.widget.*;

import com.jhm69.cse11.Model.Student;
import com.jhm69.cse11.R;
import com.jhm69.cse11.Activity.StudentDetailsActivity;
import com.squareup.picasso.*;
import java.util.*;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class StudentsRecycleAdapter extends RecyclerView.Adapter<StudentsRecycleAdapter.UsersAdapterVh> implements Filterable {

    private List<Student> studentList;
    private List<Student> getUserModelListFiltered;
    private Context context;

    public StudentsRecycleAdapter(List<Student> userModelList, Context context) {
        this.studentList = userModelList;
        this.getUserModelListFiltered = userModelList;
        this.context =context;
    }

    @NonNull
    @Override
    public StudentsRecycleAdapter.UsersAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UsersAdapterVh(LayoutInflater.from(context).inflate(R.layout.list_sample_stu,null));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsRecycleAdapter.UsersAdapterVh holder, int position) {

        final Student student = studentList.get(position);
        String imageEncodedString = studentList.get(position).getImg();

        try {
            holder.image.setImageBitmap(decodeImage(imageEncodedString));
        }catch (Exception e){
            Toast.makeText(context, "Error Loading Image of"+student.getNick(), Toast.LENGTH_SHORT).show();
        }

        holder.nameTV.setText(student.getName());
        holder.numberTV.setText(student.getPresent() );
        holder.bloodTV.setText(student.getBlood());

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StudentDetailsActivity.class);
                intent.putExtra("1234", student.getUid());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if(charSequence == null | charSequence.length() == 0){
                    filterResults.count = getUserModelListFiltered.size();
                    filterResults.values = getUserModelListFiltered;

                }else{
                    String searchChr = charSequence.toString().toLowerCase();

                    List<Student> resultData = new ArrayList<>();

                    for(Student userModel: getUserModelListFiltered){
                        if(userModel.getName().toLowerCase().contains(searchChr) ||
                                userModel.getBlood().toLowerCase().contains(searchChr) ||
                                userModel.getNick().toLowerCase().contains(searchChr) ||
                                userModel.getPresent().toLowerCase().contains(searchChr) ||
                                userModel.getNumber().toLowerCase().contains(searchChr)){
                            resultData.add(userModel);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;

                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                studentList = (List<Student>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }


    public class UsersAdapterVh extends RecyclerView.ViewHolder {

        TextView nameTV;
        TextView bloodTV;
        TextView numberTV;
        ImageView image;
        CardView item;
        public UsersAdapterVh(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.stu_name);
            bloodTV = itemView.findViewById(R.id.blood_group);
            numberTV = itemView.findViewById( R.id.stu_number);
            image = itemView.findViewById(R.id.img);
            item = itemView.findViewById(R.id.next);

        }
    }

    public static Bitmap decodeImage(String input) {
        byte[] decodeByte = Base64.decode(input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }
}