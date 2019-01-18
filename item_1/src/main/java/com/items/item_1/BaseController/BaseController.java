package com.items.item_1.BaseController;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import com.items.item_1.model.PageJSON;
import com.items.item_1.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;

public class BaseController {
    public BaseController() {
    }

    protected <T> PageJSON<T> getPageJSON(String jsonStr, Class<T> clazz) {
        PageJSON<T> pageJSON = (PageJSON) JsonUtils.parseObject(jsonStr, new TypeReference<PageJSON<T>>() {
        }, new Feature[0]);
        if (null != pageJSON.getFormJSON() && StringUtils.isNotBlank(pageJSON.getFormJSON().toString())) {
            T data = JsonUtils.parseObject(pageJSON.getFormJSON().toString(), clazz);
            pageJSON.setFormJSON(data);
        }

        return pageJSON;
    }

    protected <T> T getFormJSON(String json, Class<T> clz) {
        Object formJSOn = JsonUtils.getValueBykey(json, "formJSON");
        if (null == formJSOn) {
            return JsonUtils.parseObject(json, clz);
        } else {
            PageJSON<T> pageJSON = this.getPageJSON(json, clz);
            return pageJSON.getFormJSON();
        }
    }
}
