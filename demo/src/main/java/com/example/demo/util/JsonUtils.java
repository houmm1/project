package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.example.demo.model.PageJSON;
import org.apache.commons.lang3.StringUtils;


public class JsonUtils {
    public static <T> T parseObject(String text, TypeReference<T> type, Feature... features) {
        return JSONObject.parseObject(text, type, features);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSONObject.parseObject(text, clazz);
    }


    protected <T> PageJSON<T> getPageJSON(String jsonStr, Class<T> clazz) {
        PageJSON<T> pageJSON = (PageJSON)JsonUtils.parseObject(jsonStr, new TypeReference<PageJSON<T>>() {
        }, new Feature[0]);
        if (null != pageJSON.getFormJSON() && StringUtils.isNotBlank(pageJSON.getFormJSON().toString())) {
            T data = JsonUtils.parseObject(pageJSON.getFormJSON().toString(), clazz);
            pageJSON.setFormJSON(data);
        }

        return pageJSON;
    }

    public static Object getValueBykey(String text, String key) {
        JSONObject jsonObject = JSON.parseObject(text);
        return jsonObject.get(key);
    }


}
