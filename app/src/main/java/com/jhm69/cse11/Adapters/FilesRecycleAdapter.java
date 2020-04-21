package com.jhm69.cse11.Adapters;


import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import androidx.appcompat.widget.*;
import android.view.*;
import android.widget.*;

import com.jhm69.cse11.Model.File;
import com.jhm69.cse11.R;
import com.squareup.picasso.*;
import java.util.*;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FilesRecycleAdapter extends RecyclerView.Adapter<FilesRecycleAdapter.MyViewHolder> {

    List<File>deatil2;
    Context context;
    private CardView download;
    public FilesRecycleAdapter(List<File>deatil2, Context context){

		this.deatil2 = deatil2;
		this.context=context;

    }
	
	

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.file_sample,viewGroup,false);
	    download = view.findViewById(R.id.download);
        return new MyViewHolder(view);
		
		
    }


	
	
    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder,final int i) {
		
		
		Picasso.get()
			.load(deatil2.get(i).getimageurl())
			.into(viewHolder.Myimageurl);
        viewHolder.Myname.setText(deatil2.get(i).getName());
		viewHolder.MyDate.setText(deatil2.get(i).getDate());
   		download.setOnClickListener(new View.OnClickListener() {  // <--- here
				@Override
				public void onClick(View v) {
					
					final String link = (deatil2.get(i).getLink());
					final String file_name = (deatil2.get(i).getName());
					
					DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
					Uri uri = Uri.parse(link);

					DownloadManager.Request request = new DownloadManager.Request(uri);
					request.setTitle(file_name);
					request.setDescription("Downloading");
					request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
					request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,file_name);
					downloadmanager.enqueue(request);
					
				}

			});
			
			
        
    }
	/*
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
	@Override
	public boolean onQueryTextSubmit(String query) {
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		newText = newText.toLowerCase();
		ArrayList<Channel> newList = new ArrayList<>();
		for (Channel channel: channels){
			String channelName = channel.getmChannelName().toLowerCase();
			if (channelName.contains(newText)){
				newList.add(channel);
			}
		}
		mAdapter.setFilter(newList);
		return true;
	}
	});
	*/
    @Override
    public int getItemCount() {
        return deatil2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Myname,MyLink,MyDate;
        ImageView Myimageurl;

        public MyViewHolder(View itemView) {
            super(itemView);
			
			Myimageurl = (ImageView) itemView.findViewById(R.id.image);
            Myname =(TextView)itemView.findViewById(R.id.file_name);
			MyDate =(TextView)itemView.findViewById(R.id.files_date);
           // Myphone = (TextView)itemView.findViewById(R.id.text_phone);
			

        }
    }
}
