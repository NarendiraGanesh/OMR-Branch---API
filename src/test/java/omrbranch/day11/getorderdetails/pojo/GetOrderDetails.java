package omrbranch.day11.getorderdetails.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetOrderDetails{
    private int id;
    private String order_no;
    private String duration;
}

