package com.example.demo.service;

import com.example.demo.model.RoomModel;

public interface RoomService {
	RoomModel printRoom(String roomNo);
	RoomModel printRoom_professor(String professor_name);
	RoomModel printRoom_business(String business_name);
}
