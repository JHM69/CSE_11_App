package com.jhm69.cse11.Model;

import java.io.Serializable;
@SuppressWarnings( "serial" )
public class Student implements Serializable {
	private String birth;
	private String blood;
	private String fb;
	private String home;
	private String img;
	private String messenger;
	private String name;
	private String nick;
	private String number;
	private String present;
	private String uid;

	public Student() {
	}

	public Student(String birth, String blood, String fb, String home, String img, String messenger, String name, String nick, String number, String present, String uid) {
		this.birth = birth;
		this.blood = blood;
		this.fb = fb;
		this.home = home;
		this.img = img;
		this.messenger = messenger;
		this.name = name;
		this.nick = nick;
		this.number = number;
		this.present = present;
		this.uid = uid;
	}

	public String getBirth() {
		return birth;
	}

	public String getBlood() {
		return blood;
	}

	public String getFb() {
		return fb;
	}

	public String getHome() {
		return home;
	}

	public String getImg() {
		return img;
	}

	public String getMessenger() {
		return messenger;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public String getNumber() {
		return number;
	}
	public String getPresent() {
		return present;
	}
	public String getUid() {
		return uid;
	}

}