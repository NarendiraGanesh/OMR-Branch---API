package omrbranch.day11.getorderdetails.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetOrderDetails_Output_Pojo{
    private int status;
    private String message;
    private String currency;
    private GetOrderDetails data;
    private ArrayList<Calendar> calendar;
    private String current_date;
    private ArrayList<Product> products;
}