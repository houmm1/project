package com.items.item_1.controller;


import com.items.item_1.model.Person;
import com.items.item_1.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author houmm
 */

@RestController
@RequestMapping("/person")
public class PersonController {
    private static Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    /**
     * 添加一个人员
     *
     * @param person
     * @return
     */
    @PostMapping(value = "/add")
    public void personAdd(@RequestBody Person person) {
        personService.insertPerson(person);
    }

    /**
     * 添加一个人员
     *
     * @param url
     * @return
     */
    @PostMapping(value = "/addExcel")
    public void personAddExcel(@RequestBody String url) {
        //personService.insertPerson(person);
        System.out.println(url);
    }


    @RequestMapping(value = "/resultImport",method = RequestMethod.POST)
    @ResponseBody
    public String importResult(@RequestParam("file") MultipartFile file) throws Exception{
        String fileName = file.getOriginalFilename();
        if(personService.batchImport(fileName,file) ==0){
            System.out.println("上传文件格式不正确");
           /* resultBean.setMsg("上传文件格式不正确");
            resultBean.setCode(1); houmm@123*/
        }else{
            System.out.println("导入成功");
        }
        return "";
    }




}
