package org.viethm.json;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class JSONHelper {
    //create JSON sub element
    private static String createJSONElement (Field field, Object obj, int numOfTab) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
       JSONKeyMapper ann = field.getAnnotation(JSONKeyMapper.class);
        Class fieldType = field.getType();
       //Get field name
       String keyAttr = ann.key();
       //Get value of field
       String value = JSONMapperUtils.getValueOfField(field, obj);
       //Get number of Tab
       String tabs = JSONMapperUtils.getNumOfTab(numOfTab);
       sb.append(JSONMapperUtils.getNumOfTab(numOfTab+1));
       sb.append("\"").append(keyAttr).append("\" : ");
       if (!fieldType.getName().equals("java.lang.String")) {
           sb.append(value);
       } else {
           sb.append("\"").append(value).append("\"");
       }
       sb.append(",");
        return sb.toString();
    }

    //create JSON wrapper element
    private static String createJSONWrapper (Field field, Object obj, int numOfTab) {
        StringBuilder sb = new StringBuilder();
        JSONKeyWrapperMapper ann = field.getAnnotation(JSONKeyWrapperMapper.class);
        String keyWrapper = ann.key();
        Collection<?> collection = JSONMapperUtils.getListValueOfField(field, obj);
        sb.append("\n");
        sb.append(JSONMapperUtils.getNumOfTab(numOfTab+1));
        sb.append("\""+keyWrapper+"\":"+" [ \n");
        //Loop through every object inside list and create JSON for it
        if (!collection.isEmpty()) {
            for (Object c: collection) {
                sb.append(convertToJson(c, numOfTab+2));
            }
        }
        sb.append(JSONMapperUtils.getNumOfTab(numOfTab+1));
        sb.append("]");
        return sb.toString();
    }

    /*
    * return String in JSON format
    * Basic idea:
    * Step 1: check if class have annotation JSONRootKEyMapper
    * Step 2: get all fields of that class
    * Step 3: loop through all fields to check if that fields have @JSONKeyMapper or @JSONKeyWrapperMapper
    * */
    public static String convertToJson(Object obj, int numOfTab) {
        StringBuilder sb = new StringBuilder();
        //get class of input object
        Class<?> clazz = obj.getClass();

        //check if class have annotation JSONRootKeyMapper
        if(clazz.isAnnotationPresent(JSONRootKeyMapper.class)) {
            //get rootKey
            JSONRootKeyMapper rootKey = clazz.getAnnotation(JSONRootKeyMapper.class);
            String key = rootKey.key();
            sb.append(JSONMapperUtils.getNumOfTab(numOfTab));
            sb.append("{\n");
            sb.append(JSONMapperUtils.getNumOfTab(numOfTab+1));
            sb.append("\""+key+"\" : {");
            List<Field> fields = JSONMapperUtils.getFields(obj.getClass(), null);
            if(!fields.isEmpty()) {
                for (Field f: fields) {
                    //Set Accessible to private or protected fields
                    f.setAccessible(true);
                    if(f.isAnnotationPresent(JSONKeyWrapperMapper.class)) {
                        sb.append(createJSONWrapper(f, obj, numOfTab+1));
                    } else if(f.isAnnotationPresent(JSONKeyMapper.class)) {
                        sb.append(createJSONElement(f, obj, numOfTab+1));
                    }
                }
            }
            sb.append("\n"+JSONMapperUtils.getNumOfTab(numOfTab)+"},\n");
        }
        return sb.toString();
    }
}
