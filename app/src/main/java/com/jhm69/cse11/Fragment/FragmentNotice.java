package com.jhm69.cse11.Fragment;
import android.os.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.*;
import androidx.core.widget.*;
import androidx.appcompat.widget.*;
import android.view.*;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jhm69.cse11.Model.Notice;
import com.jhm69.cse11.Others.MySingleTon;
import com.jhm69.cse11.R;
import com.jhm69.cse11.Adapters.NoticeRecycleAdapter;

import java.util.*;
import org.json.*;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class FragmentNotice extends Fragment {
    private DatabaseReference mainDB;
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	private NoticeRecycleAdapter recycleAdapter;
	private List<Notice> allNotices;
	private FloatingActionButton addNotice;

	
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_notices, container, false);
		addNotice =view.findViewById(R.id.addNotice);

		recyclerView = view.findViewById( R.id.recycle_view );
		layoutManager = new LinearLayoutManager( getActivity() );
		recyclerView.setLayoutManager( layoutManager );
		recyclerView.setHasFixedSize( true );
		allNotices = new ArrayList<>();
		mainDB = FirebaseDatabase.getInstance().getReference();

		getAllNotices();
		addNotice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentAddPost addPost = FragmentAddPost.newInstance();
				addPost.show(getFragmentManager(),"");
			}
		});

       return view;
    }
	private void getAllNotices() {
		mainDB.child("0").child("Notices").addChildEventListener( new ChildEventListener() {
			@Override
			public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
				Notice newnotice = dataSnapshot.getValue(Notice.class);
				allNotices.add(newnotice);
				recycleAdapter = new NoticeRecycleAdapter(allNotices,getActivity());
				recyclerView.setAdapter(recycleAdapter);
				recycleAdapter.notifyDataSetChanged();
			}

			@Override
			public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

			}

			@Override
			public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

			}

			@Override
			public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		} );

	}

}
