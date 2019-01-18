package com.items.item_1.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by houmm
 * Time: 2019/01/04 17:56
 */
@Data
@Entity
public class Person{
    @Id
    @GeneratedValue
    private  Long id;
    private String name;
    private String age;

}