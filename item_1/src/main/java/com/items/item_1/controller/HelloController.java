package com.items.item_1.controller;

import com.items.item_1.Dto.PersonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by houmm
 * Time: 2019/01/04 15:45
 */
@RestController
public class HelloController {

    @Autowired
    private PersonProperties personProperties;


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String say(){
        return personProperties.getName()+personProperties.getAge();
    }


}
