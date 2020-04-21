package com.jhm69.cse11.Fragment;
import android.os.*;

import androidx.annotation.Nullable;
import androidx.core.app.*;
import androidx.appcompat.widget.*;
import android.view.*;

import com.jhm69.cse11.R;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class FtagmentSlybsThirdYear extends Fragment {

	CardView x;
	
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sl3, container, false);


    }
	/*@Override
	 public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
	 super.onViewCreated(view, savedInstanceState);
	 ImageView iamge = (ImageView) view.findViewById(R.id.vector); */


	@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


	}




	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



		//x.setRadius(10);

	}

}

