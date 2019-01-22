package com.items.item_1.service;


import com.items.item_1.model.Person;
import java.io.InputStream;
import java.util.List;

public interface PersonService {
     void insertPerson(Person stu);

     Integer batchImport(String fileName, InputStream is) throws Exception;

     List<Person> personList(Person per) throws Exception;
}
