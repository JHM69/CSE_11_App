package com.jhm69.cse11.Adapters;


import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jhm69.cse11.Model.Notice;
import com.jhm69.cse11.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NoticeRecycleAdapter extends RecyclerView.Adapter<NoticeRecycleAdapter.MyViewHolder> {

    List<Notice>notices;
    Context context;

    public NoticeRecycleAdapter(List<Notice>notices, Context context){

		this.notices = notices;
		this.context=context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notice,viewGroup,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {

        viewHolder.Myname.setText(notices.get(i).getName());
		viewHolder.MyDate.setText(notices.get(i).getDate());
        viewHolder.MyBody.setText(notices.get(i).getText());
        String imglink = notices.get(i).getImage();


        viewHolder.Myimageurl.setImageBitmap(decodeImage(imglink));

    }

    @Override
    public int getItemCount() {
        return notices.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Myname,MyBody,MyDate;
        ImageView Myimageurl;

        public MyViewHolder(View itemView) {
            super(itemView);

            Myname =(TextView)itemView.findViewById(R.id.text_name);
			MyDate =(TextView)itemView.findViewById(R.id.date);
            MyBody = (TextView)itemView.findViewById(R.id.bodyTV );
			Myimageurl = (ImageView) itemView.findViewById(R.id.image_url);
			

        }
    }

    public static Bitmap decodeImage(String input) {
        byte[] decodeByte = Base64.decode(input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }

}
