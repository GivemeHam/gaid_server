package com.example.demo.controller;

import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.RoomModel;
import com.example.demo.service.RoomService;

@Controller
//@MapperScan(basePackages = "com.example.demo.dao")
public class GaidController {

	@Autowired
	RoomService roomService;
	
	@RequestMapping("/test2")
	public String test() {
		System.out.println("test");
		return "OK";
	}
	
	//jsp 없이 사진띄우기
	@PostMapping("/viewImage")
	public ResponseEntity<byte[]> view_image(@RequestParam("image_name") String image_name) throws IOException {
		ClassPathResource imageFile = new ClassPathResource("static/images/" + image_name + ".jpg");

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}
	
	//형태소 분석
	@RequestMapping("/getmorph")
	public String morph(@RequestParam("morph") String morph, Model model) {
		String res = null;
		res = morph;
		System.out.println("morph log : " + res);
		
		model.addAttribute("res",res);
		
		return "getmorph"; 
	}
	
	
	@RequestMapping("/test") // URL 주소
	public String list(Model model) {

		RoomModel room = roomService.printRoom();
		System.out.println("test : " + room.getRoom_idx());
		model.addAttribute("idx", room.getRoom_idx());
		model.addAttribute("floor", room.getRoom_floor());
		model.addAttribute("name", room.getRoom_name());
		model.addAttribute("professor", room.getRoom_professor());
		
		return "test"; // JSP 파일명
	}
	
	@RequestMapping("/test3")
	public ResponseEntity<byte[]> view_image2() throws IOException {
		ClassPathResource imageFile = new ClassPathResource("static/images/n1593595036907.jpg");

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}	
	
}

