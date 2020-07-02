package com.example.demo.model;

public class MorphModel {
	private int morph_idx;
	private String morph_func;	//기능
	private String morph_morph;	//형태소
	private String morph_pos;	//품사
	
	public int getMorph_idx() {
		return morph_idx;
	}
	public void setMorph_idx(int morph_idx) {
		this.morph_idx = morph_idx;
	}
	public String getMorph_func() {
		return morph_func;
	}
	public void setMorph_func(String morph_func) {
		this.morph_func = morph_func;
	}
	public String getMorph_morph() {
		return morph_morph;
	}
	public void setMorph_morph(String morph_morph) {
		this.morph_morph = morph_morph;
	}
	public String getMorph_pos() {
		return morph_pos;
	}
	public void setMorph_pos(String morph_pos) {
		this.morph_pos = morph_pos;
	}
}
