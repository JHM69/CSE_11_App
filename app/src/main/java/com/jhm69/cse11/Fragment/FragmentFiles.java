package com.jhm69.cse11.Fragment;
import android.content.pm.*;
import android.os.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.*;
import androidx.appcompat.widget.*;
import android.view.*;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.jhm69.cse11.Model.File;
import com.jhm69.cse11.R;
import com.jhm69.cse11.Adapters.FilesRecycleAdapter;

import java.util.*;
import org.json.*;
import android.*;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentFiles extends Fragment {
	
	private String JSON_URL="https://raw.githubusercontent.com/JHM69/CSE-11-JnU/master/files.json";
	

    private RecyclerView recyclerView2;
	private RecyclerView.LayoutManager layoutManager2;
	private FilesRecycleAdapter recycleAdapter;
	private List<File> deatil2;
	
	private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.files, container, false);


    }
	@Override
	public void onStart()
	{
		recyclerView2 = (RecyclerView)getActivity().findViewById(R.id.recycle_view2);
        layoutManager2 = new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setHasFixedSize(true);
        deatil2 = new ArrayList<>();
        getDetailJSON();
		
		// TODO: Implement this method
		super.onStart();
	}


	@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
	    requestStoragePermission();
	
	}
		
	
	
	
	
	private void getDetailJSON() {

        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
				@Override
				public void onResponse(JSONArray response2) {

					for (int i =0;i<response2.length();i++){
						try {
							JSONObject object2= response2.getJSONObject(i);
							File mydetail = new File(object2.getString("image"),object2.getString("file"), object2.getString("date"),object2.getString("link"));
							deatil2.add(mydetail);
							recycleAdapter = new FilesRecycleAdapter(deatil2,getActivity());
							recyclerView2.setAdapter(recycleAdapter);
							Toast.makeText(getActivity(),"Make sure your are in online.",Toast.LENGTH_SHORT).show();  

							
							
							//String x = (deatil2.get(i).getLink());
							//Toast.makeText(getActivity(),x,Toast.LENGTH_SHORT).show();  
							
						} catch (JSONException e) {
							e.printStackTrace();
				
							Toast.makeText(getActivity(),"Error...",Toast.LENGTH_SHORT).show();  
							
						}


					}

				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {

				}
			});
		RequestQueue requestQueue2 = Volley.newRequestQueue(getActivity());
        requestQueue2.add(jsonArrayRequest2);
    }

	private void requestStoragePermission(){
		
		
		
		if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), 
																android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {

        }
        
		int STORAGE_PERMISSION_CODE = 0;
		ActivityCompat.requestPermissions(getActivity(), 
										  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
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
			

}}}

