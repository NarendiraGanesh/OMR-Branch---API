package omrbranch.day11.getorderstatus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product{
    private int id;
    private String image;
    private String medium_image;
    private String name;
    private String price;
    private String special_price;
}

