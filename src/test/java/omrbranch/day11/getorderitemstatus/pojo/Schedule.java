package omrbranch.day11.getorderitemstatus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Schedule{
    private int id;
    private String date;
    private String status;
}