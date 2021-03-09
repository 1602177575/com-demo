package com.smart.swager.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @ApiOperation(value = "用户测试接口",notes = "说明")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "Long",paramType = "path")
    @RequestMapping(value = "/test/{id}",method = RequestMethod.GET)
    public String test(@PathVariable(value = "id") Integer id){
        return "测试";
    }

}
