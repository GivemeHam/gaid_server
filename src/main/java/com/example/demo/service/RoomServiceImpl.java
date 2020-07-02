package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoomDao;
import com.example.demo.model.RoomModel;


@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;
	
	@Override
	public RoomModel printRoom(String roomNo) {
		RoomModel room = roomDao.getRoom(roomNo);
		return room;
	}
}