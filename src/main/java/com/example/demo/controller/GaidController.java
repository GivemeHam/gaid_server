package com.example.demo.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GaidController {

	@RequestMapping("/test")
	public String test() {
		System.out.println("test");
		return "OK";
	}
	
	@PostMapping("/viewImage")
	public ResponseEntity<byte[]> view_image(@RequestParam("image_name") String image_name) throws IOException {
		ClassPathResource imageFile = new ClassPathResource("static/images/" + image_name + ".jpg");

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}
	
	@RequestMapping("/test2")
	public ResponseEntity<byte[]> view_image2() throws IOException {
		ClassPathResource imageFile = new ClassPathResource("static/images/n1593595036907.jpg");

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}	
	
}

