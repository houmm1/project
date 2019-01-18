package com.items.item_1.Dao;


import com.items.item_1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by houmm
 * Time: 2019/01/04 16:34
 */
public interface PersonDAO extends JpaRepository<Person,Integer> {


}
