package omrbranch.day11.getorderstatus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order{
    private String amount;
    private boolean wallet_show;
    private String wallet_amount;
    private String remaining_amount;
    private String order_no;
    private int id;
}

