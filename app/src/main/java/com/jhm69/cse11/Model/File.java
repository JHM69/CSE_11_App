package com.jhm69.cse11.Model;

public class File {
	
	String imageurl2;
    String name2;    
	String date2;
	String link;
    public File(String imageurl, String name, String date, String link){
		
		this.imageurl2=imageurl;
        this.name2 = name;     
		this.date2 = date;
		this.link = link;
    }
	public String getimageurl() {
        return imageurl2;
    }
    public String getName() {
        return name2;
    } 
	public String getDate(){
		return date2;
	}
	public String getLink(){
		return link;
	}
}
