package omrbranch.day11.getorderitemstatus.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetOrderItemStatus_Output_Pojo{
    private int status;
    private String message;
    private String currency;
    private Products products;
    private ArrayList<Schedule> schedules;
    private Order order;
}

