package com.items.item_1.Dto;

import com.items.item_1.model.Person;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by houmm
 * Time: 2019/01/18 16:34
 */

@Component
@Data
public class ResultInfo {
    private Long id;
    private Date createDate;
    private Person per;
}
