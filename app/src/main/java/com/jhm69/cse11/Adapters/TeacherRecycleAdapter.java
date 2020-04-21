package com.jhm69.cse11.Adapters;


import android.content.*;
import android.net.*;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.*;
import android.view.*;
import android.widget.*;

import com.jhm69.cse11.Model.Teacher;
import com.jhm69.cse11.R;
import com.squareup.picasso.*;
import java.util.*;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class TeacherRecycleAdapter extends RecyclerView.Adapter<TeacherRecycleAdapter.UsersAdapterVh> implements Filterable {

    private List<Teacher> studentList;
    private List<Teacher> getUserModelListFiltered;
    private Context context;

    public TeacherRecycleAdapter(List<Teacher> userModelList, Context context) {
        this.studentList = userModelList;
        this.getUserModelListFiltered = userModelList;
        this.context =context;
    }

    @NonNull
    @Override
    public TeacherRecycleAdapter.UsersAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new UsersAdapterVh(LayoutInflater.from(context).inflate(R.layout.teacher_each,null));
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherRecycleAdapter.UsersAdapterVh holder, int position) {

        final Teacher teacher = studentList.get(position);

        Picasso.get().load(teacher.getImg()).into(holder.image);


        holder.nameTV.setText(teacher.getName());
        holder.asTV.setText(teacher.getAs() );
        holder.mailTV.setText(teacher.getEmail());

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(teacher.getLink()));
                v.getContext().startActivity(browserIntent);
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

                    List<Teacher> resultData = new ArrayList<>();

                    for(Teacher userModel: getUserModelListFiltered){
                        if(userModel.getName().toLowerCase().contains(searchChr) || userModel.getAs().toLowerCase().contains(searchChr)){
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

                studentList = (List<Teacher>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }


    public class UsersAdapterVh extends RecyclerView.ViewHolder {

        TextView nameTV;
        TextView asTV;
        TextView mailTV;
        ImageView image;
        CardView item;
        public UsersAdapterVh(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.namet);
            asTV = itemView.findViewById(R.id.ast);
            mailTV = itemView.findViewById( R.id.mailt);
            image = itemView.findViewById(R.id.imgt);
            item = itemView.findViewById(R.id.link);

        }
    }

}