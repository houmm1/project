package com.example.demo.model;

import lombok.Data;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by houmm
 * Time: 2019/01/04 17:56
 */

@Data
@Entity
@EntityListeners({AuditingEntityListener.class})
public class Person implements Persistable {
    @Id
    @GeneratedValue
    private  Long id;
    private String name;
    private String age;

    @Override
    public boolean isNew() {
        return false;
    }
}