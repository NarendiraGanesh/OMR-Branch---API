package omrbranch.day11.trackorder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order{
	
	private String order_no;
	private String duration;
	private String delivery_slot;
	
}

