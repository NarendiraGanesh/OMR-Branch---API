package com.omrbranch.day3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omrbranch.day3.pojo.Datum;
import com.omrbranch.day3.pojo.Sample;
import com.omrbranch.day3.pojo.Support;

public class Day3_U0301 {
	
	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		File file = new File("D:\\Java Training\\Practice\\OMRBranchAPITasks_NarendiraGanesh\\src\\test\\resources\\test.json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<Datum> data = new ArrayList<Datum>();
		Datum d1 = new Datum(49, "AirIndia", "India", "85", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		Datum d2 = new Datum(481, "Lufthansa", "India", "87", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		Datum d3 = new Datum(516, "AIR CANADA", "India", "19", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		Datum d4 = new Datum(548, "AirIndia", "India", "87", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		Datum d5 = new Datum(557, "AirIndia", "India", "87", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		Datum d6 = new Datum(561, "AirIndia", "India", "87", "https:\\/\\/en.wikipedia.org\\/wiki\\/Air_India");
		
		data.add(d1);
		data.add(d2);
		data.add(d3);
		data.add(d4);
		data.add(d5);
		data.add(d6);
		
		Support support = new Support("https:\\/\\/www.omrbranch.com", "For Joining Automation Course, Please Contact-Velmurugan 9944152058");
		
		Sample sample = new Sample(1, 6, 14261, 2377, data, support);
		
		mapper.writeValue(file, sample);
		
		
	}

}
