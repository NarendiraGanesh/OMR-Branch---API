package omrbranch.day11.trackorder.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TrackOrder_Output_Pojo{
    private int status;
    private String message;
    private String currency;
    private Products products;
    private ArrayList<Schedule> Schedules;
    private Order order;
}
