package com.smart.log.pojo;

import com.smart.log.Action.MethodLogAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MethodLogAction(date = "date测试",name = "测试")
public class TestLog {

    public String name;

    public String date;

}
