package com.items.item_1.controller;


import com.alibaba.fastjson.JSONObject;
import com.items.item_1.model.Person;
import com.items.item_1.service.PersonService;
import com.items.item_1.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author houmm
 */

@RestController
@RequestMapping("/person")
public class PersonController {
    private static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

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
        JSONObject jo = new JSONObject();
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();

        if(personService.batchImport(fileName,inputStream) ==0){
            LOGGER.info("上传文件格式不正确");
            jo.put("code","999999");
            jo.put("message","FAIL");
        }else{
            LOGGER.info("导入成功");
            jo.put("code","000000");
            jo.put("message","SUCCESS");
        }
        return JSONObject.toJSONString(jo);

    }

    /**
     * 导出报表
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(@RequestBody Person person, HttpServletRequest request, HttpServletResponse response) throws Exception {

        LOGGER.info("导出报表参数:%s", JSONObject.toJSONString(person));
        //获取部门记录信息
        List<Person> result = personService.personList(person);

        //excel标题
        String[] title = {"姓名","年龄"};

        //excel文件名
        String fileName = "用户信息表"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "用户信息表";
        String [][] content = new String[result.size()][title.length];
        for (int i = 0; i < result.size(); i++) {
            Person obj = result.get(i);
            content[i][0] = obj.getName();
            content[i][1] = obj.getAge();
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
                OutputStream os = response.getOutputStream();
                wb.write(os);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        try {
            fileName = new String(fileName.getBytes(),"ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
