package com.example.board;

import android.graphics.drawable.Drawable;

public class InfoClass {
	public String title;
	public Drawable image;
	public String button;
	public String content;
	
	public InfoClass(String title, Drawable image, String button, String content){
		this.title = title;
		this.image = image;
		this.button = button;
		this.content = content;
	}
}
