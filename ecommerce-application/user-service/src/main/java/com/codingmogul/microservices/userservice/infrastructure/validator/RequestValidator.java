package com.codingmogul.microservices.userservice.infrastructure.validator;

import org.json.JSONObject;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;

public class RequestValidator {
    public static void checkNullFields(Object object) throws RequestFieldValidateException {
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            Object o = null;
            try {
                o = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if(field.isAnnotationPresent(NotNull.class) && o == null){
                JSONObject json = new JSONObject();
                json.put("fieldName", field.getName());
                json.put("message", "can not be null");
                throw new RequestFieldValidateException(json.toString());
            }

            if(field.isAnnotationPresent(NotEmpty.class) && ObjectUtils.isEmpty(o)){
                JSONObject json = new JSONObject();
                json.put("fieldName", field.getName());
                json.put("message", "can not be empty");
                throw new RequestFieldValidateException(json.toString());
            }
        }
    }

}
