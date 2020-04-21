package com.jhm69.cse11.Others;


import android.content.*;
import com.android.volley.*;
import com.android.volley.toolbox.*;

public class MySingleTon
 {
    private static MySingleTon mySingleTon;
    private RequestQueue requestQueue;
    private static Context mctx;
    private MySingleTon(Context context){
        this.mctx=context;
        this.requestQueue=getRequestQueue();

    }
    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }
	public static synchronized MySingleTon getInstance(Context context){
		if (mySingleTon==null){
			mySingleTon=new MySingleTon(context);
		}
		return mySingleTon;
	}
    public<T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);

    }
}
