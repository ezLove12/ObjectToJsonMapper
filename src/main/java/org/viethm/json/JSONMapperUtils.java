package org.viethm.json;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JSONMapperUtils {
    //get number of Tab in front of JSON Element
    public static String getNumOfTab(int numOfTab) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numOfTab; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }

    //Check is empty String
    public static boolean isNotEmptyString(String str) {
        return str!=null|| !str.isEmpty();
    }

    /*
    * return a list of value of specific fields
    * */
    //Get value of Collections
    public static Collection<?> getListValueOfField(Field field, Object obj) {
        Collection<?> collection = null;
        try {
            //Get collection value of field
            Object objValue = field.get(obj);
            if (objValue instanceof Collection ) {
                collection = (Collection<?>) objValue;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return collection;
    }

    /*
    * return a value of a specific value
    * input: field, object
    * */
    public static String getValueOfField(Field field, Object obj) {
        String value;
        try {
            //get value of field
            value = String.valueOf(field.get(obj));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    //get all fields of object have of haven't specific annotations
    public static List<Field> getFields(Class<?> clazz, Class<? extends Annotation> ann) {
        List<Field> fieldList = new ArrayList<>();

        Field[] declaredFields = clazz.getDeclaredFields();

        if(ann == null ){
            fieldList.addAll(Arrays.asList(declaredFields));
        }else{
            for (Field f: declaredFields) {
                if(f.isAnnotationPresent(ann)) fieldList.add(f);
            }
        }
        return fieldList;
    }
}
