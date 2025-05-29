package omrbranch.day11.getorderitemstatus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Products{
    private int id;
    private String image;
    private String medium_image;
    private String name;
    private String price;
    private String start_date;
    private String end_date;
    private int scheduled;
    private String special_price;
}

