package com.omrbranch.day12.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChangeProfilePic {

    private int id;
    private String user_code;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile_number;
    private String password;
    private String api_key;
    private int days;
    private String month;
    private String year;
    private String gender;
    private String hobbies;
    private String forgot_key;
    private String profile_picture;
    private String login_cookie;
    private int google_signin;
    private String google_auth_id;
    private int facebook_signin;
    private String facebook_id;
    private String referral_code;
    private int wallet_amount;
    private Object device_id;
    private Object device_token;
    private int is_verified;
    private int admin_verify;
    private int admin_verified;
    private String flight_access;
    private Object device_type;
    private String status;
    private String travel_access;
    private String grocery_front_access;
    private String travel_with_api_access;
    private String sample_api_access;
    private String database_access;
    private String admin_panel_access;
    private String mobile_app_access;
    private String created_at;
    private String updated_at;
    private String refer_screen;
    
}