package com.jhm69.cse11.Model;

public class Teacher {

	String as;
	String mail;
	String img;
	String link;
	String name;

	public Teacher(String as, String mail, String img, String link, String name) {
		this.as = as;
		this.mail = mail;
		this.img = img;
		this.link = link;
		this.name = name;
	}

	public Teacher() {
	}

	public String getAs() {
		return as;
	}

	public String getEmail() {
		return mail;
	}

	public String getImg() {
		return img;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}
}