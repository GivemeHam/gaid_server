package com.example.demo.dao;

import com.example.demo.model.MorphModel;

public interface MorphDao {
	MorphModel getMorph(String token_name, String morph_name);
}
