package com.items.item_1.service.impl;

import com.google.common.collect.Lists;
import com.items.item_1.Dao.PersonDAO;
import com.items.item_1.Dto.ResultInfo;
import com.items.item_1.model.Person;
import com.items.item_1.model.QPerson;
import com.items.item_1.service.PersonService;
import com.items.item_1.util.SnowFlakeKeyGen;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDAO personDAO;

    @Override
    public void insertPerson(Person per) {
        personDAO.save(per);
    }


    //客户批量导入
    @Override
    public Integer batchImport(String fileName, InputStream is) throws Exception{
        boolean notNull = false;
        Integer status = 1;
        List<Person> resultList = new ArrayList<>();

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            String error = "上传文件格式不正确";
            status = 0;
            return status;
        }
        boolean isExcel2003 = true;
       if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }

        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        System.out.println(sheet.getLastRowNum());
        for (int r = 0; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            ResultInfo resultInfo = new ResultInfo();
            Person per = new Person();
            per.setId(new SnowFlakeKeyGen().nextId());
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);//设置读取转String类型
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);

            String age = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();

            if(name ==null || age==null){
                continue;
            }

            per.setName(name);
            per.setAge(age);

            System.out.println(r + name);
            resultList.add(per);
            personDAO.save(per);
            //resultInfoRepository.save(resultInfo);

        }
        return status;
    }

    @Override
    public List<Person> personList(Person per) throws Exception {
        List<Person> list = personDAO.findPerson();
       // List<Person> list = Lists.newArrayList(ite);
        return list;
    }

    /**
     * 拼装断言
     * @param  dto
     * @return
     */
    private Predicate getInputCondition(Person dto) {
        List<BooleanExpression> predicates = new ArrayList<>();
        if (null != dto.getName() && !"".equals(dto.getName())) {
            predicates.add(QPerson.person.name.eq(dto.getName()));
        }
        if (null != dto.getAge() && !"".equals(dto.getAge())) {
            predicates.add(QPerson.person.age.eq(dto.getAge()));
        }

        Predicate predicate=ExpressionUtils.allOf(predicates.toArray(new BooleanExpression[predicates.size()]));
        return predicate;

    }


}
