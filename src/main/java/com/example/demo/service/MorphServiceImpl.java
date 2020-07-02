package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MorphDao;
import com.example.demo.model.MorphModel;

@Service
public class MorphServiceImpl implements MorphService{
	@Autowired
	private MorphDao morphDao;
	
	@Override
	public MorphModel printMorph(String token_name, String morph_name) {
		System.out.println("impl test : " + token_name + "/" + morph_name);
		MorphModel model = morphDao.getMorph(token_name, morph_name);
		return model;
	}

	
}
