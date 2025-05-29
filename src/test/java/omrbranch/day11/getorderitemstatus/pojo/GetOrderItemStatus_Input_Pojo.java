package omrbranch.day11.getorderitemstatus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetOrderItemStatus_Input_Pojo {

	private String order_id;
	private String item_id;

}
