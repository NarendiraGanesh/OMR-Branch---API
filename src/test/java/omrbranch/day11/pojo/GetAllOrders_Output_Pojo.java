package omrbranch.day11.pojo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetAllOrders_Output_Pojo {
    private int status;
    private String message;
    private String currency;
    private ArrayList<GetAllOrders> data;

}
