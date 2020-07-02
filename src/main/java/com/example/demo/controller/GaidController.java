package com.example.demo.controller;

import java.io.IOException;
import java.util.StringTokenizer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.MorphModel;
import com.example.demo.model.RoomModel;
import com.example.demo.service.MorphService;

import com.example.demo.service.RoomService;
import com.example.demo.util.*;

@Controller
//@MapperScan(basePackages = "com.example.demo.dao")
public class GaidController {


	@Autowired
	MorphService morphService;
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
	@PostMapping("/getmorph")
	public String morph(@RequestParam("morph") String morph, Model model) throws JSONException {
		System.out.println("형결짱짱 : ");
		String res = null;
		MorphUtil morphUtil = new MorphUtil();
		String sn = "0";
		System.out.println("형결짱짱 : " + morph);
		JSONObject jo = new JSONObject();
		//분석
		String morph_res = morphUtil.komoran(morph);					//  안녕/NNG 하/XSV 시/EP 어요/EC 절/VV 는/ETM 주/NNP 용/XSN 이/VCP ㅂ니다/EC
		StringTokenizer token1 = new StringTokenizer(morph_res, " ");
	    while(token1.hasMoreTokens()) {
	    	StringTokenizer token_temp = new StringTokenizer(token1.nextToken(), "/");
	    	String token_name = token_temp.nextToken();
	    	String morph_name = token_temp.nextToken();
	    	
	    	if(morph_name.equals("SN")) {		//숫자 넣어놓기
	    		sn = token_name;
	    	}
	       // System.out.println("Test : " + token_name + morph_name); //" first : token, second : morph_name
	        
	        MorphModel morph_model = morphService.printMorph(token_name, morph_name);
	        if(morph_model != null) {
	        //	System.out.println("morph_model.getMorph_func() : " + morph_model.getMorph_func());
	        	jo.put("function", morph_model.getMorph_func()); 		//navi, info, cam
	        	jo.put("roomNo", sn);
	        	break;
	        }
	        
	    }
	    model.addAttribute("func",jo.toString());
		/* MorphModel morph_model = morphService.printMorph("안녕", "NNG");
	     System.out.println("morph_model.getMorph_func() : " + morph_model.getMorph_func());
	     model.addAttribute("func",morph_model.getMorph_func());
		
		model.addAttribute("res",res);
		*/
		return "getmorph"; 
	}
	
	
	@RequestMapping("/room") // URL 주소
	public String roomInfo(@RequestParam("roomNo") String roomNo, Model model) throws JSONException{
		System.out.println("testtest");
		RoomModel room = roomService.printRoom(roomNo);
		JSONObject jo = new JSONObject();

		jo.put("room_idx",room.getRoom_idx());
		jo.put("room_no",room.getRoom_no());
		jo.put("room_floor",room.getRoom_floor());
		jo.put("room_department",room.getRoom_department());
		jo.put("room_professor",room.getRoom_professor());
		jo.put("room_tel",room.getRoom_tel());
		jo.put("room_name",room.getRoom_name());
		jo.put("room_business",room.getRoom_business());
		System.out.println("test : " + jo.toString());
		model.addAttribute("res", jo.toString());
		
		return "room"; // JSP 파일명
	}
	
	@GetMapping("/view_img")
	public String view_img(@RequestParam("img_name") String img_name, Model model) {
		System.out.println("view img test : " + img_name);
		model.addAttribute("img_name", img_name);
		
		return "view_img";
	}
	
	@RequestMapping("/test3")
	public ResponseEntity<byte[]> view_image2() throws IOException {
		ClassPathResource imageFile = new ClassPathResource("static/images/n1593595036907.jpg");

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}	
	
}

