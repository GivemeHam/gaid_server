package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class GaidController {

	@RequestMapping("/")
	public String test() {
		System.out.println("test");
		return "test.html";
	}
	
	@PostMapping("/uploadPhoto")
	public String uploadPhoto(@RequestPart(name = "img") MultipartFile img) {
		System.out.println("data : " + img.getOriginalFilename());
		return "OK";
	}
	
}

