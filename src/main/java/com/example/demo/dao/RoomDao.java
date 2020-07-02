package com.example.demo.dao;

import com.example.demo.model.RoomModel;

public interface RoomDao {
	RoomModel getRoom(String roomNo);
	RoomModel getRoom_professor(String professor_name);
	RoomModel getRoom_business(String business_name);
}
