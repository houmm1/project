package com.items.item_1.Dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by houmm
 * Time: 2019/01/04 16:34
 */

@Component
@Data
public class PersonProperties {
    private String name;
    private Integer age;
}
