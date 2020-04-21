package com.jhm69.cse11.Fragment;
import android.app.*;
import android.os.*;

import androidx.annotation.Nullable;
import androidx.core.app.*;
import androidx.appcompat.widget.*;
import android.util.*;
import android.view.*;
import android.webkit.*;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.jhm69.cse11.R;

public class FragmentSlybsFirstYear extends Fragment {
	WebView mWebView;
	ProgressDialog progressDialog;
	CardView x;
	
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sl1, container, false);
   

    }
	/*@Override
	 public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
	 super.onViewCreated(view, savedInstanceState);
	 ImageView iamge = (ImageView) view.findViewById(R.id.vector); */


	@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		
		mWebView = (WebView) getActivity().findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebChromeClient(new WebChromeClient() {
				public boolean onConsoleMessage(ConsoleMessage cm) {
					String TAG = null;
					Log.d(TAG, cm.message() + " -- From line "
						  + cm.lineNumber() + " of "
						  + cm.sourceId() );
					return true;
				}

			});
		mWebView.loadUrl("file:///android_asset/slybs.html");
		mWebView.loadUrl("javascript:plot()");
    }




	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


	
		//x.setRadius(10);
}
}

