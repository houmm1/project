package com.items.item_1.service;


import com.items.item_1.model.Person;
import org.springframework.web.multipart.MultipartFile;

public interface PersonService {
     void insertPerson(Person stu);

     Integer batchImport(String fileName, MultipartFile file) throws Exception;
}
