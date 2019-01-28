package com.example.demo.Dao;
import com.example.demo.model.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by houmm
 * Time: 2019/01/04 16:34
 */
public interface PersonDAO extends JpaRepository<Person,Integer>{
    /**
     * 查询用户信息
     * @param
     * @return
     */
    @Query(value = "select * from person",nativeQuery = true)
    List<Person> findPerson();

    /**
     * 分页
     * @param specification
     * @param specification
     * @return
     */
    List<Person> findAll(Specification<Person> specification);
}
