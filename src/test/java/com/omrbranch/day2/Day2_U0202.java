package com.omrbranch.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omrbranch.day2.pojo.Data;
import com.omrbranch.day2.pojo.Pivot;
import com.omrbranch.day2.pojo.Sample;
import com.omrbranch.day2.pojo.UserRole;

public class Day2_U0202 {
	
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
		File file = new File("D:\\Java Training\\Practice\\OMRBranchAPITasks_NarendiraGanesh\\src\\test\\resources\\Greens.json");
		ObjectMapper mapper = new ObjectMapper();
		Sample value = mapper.readValue(file, Sample.class);
		
		Data data = value.getData();
		String last_name = data.getLast_name();
		System.out.println("Last Name:\t"+last_name);
		String email = data.getEmail();
		System.out.println("Email:\t"+email);
		
		ArrayList<UserRole> user_role = data.getUser_role();
		for (int i = 0; i < user_role.size(); i++) {
			UserRole userRole = user_role.get(0);
			String updated_at = userRole.getUpdated_at();
			System.out.println("Updated At:\t"+updated_at);
			
			Pivot pivot = userRole.getPivot();
			int role_id = pivot.getRole_id();
			System.out.println("Role Id:\t"+role_id);
		}
		
		String refer_msg = value.getRefer_msg();
		System.out.println("Refer Message:\t"+refer_msg);
		
	}
}
