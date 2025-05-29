package com.omrbranch.day3.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Datum {

	private int id;
    private String flightName;
    private String country;
    private String destinations;
    private String url;
}
