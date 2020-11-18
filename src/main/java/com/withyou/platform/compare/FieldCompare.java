package com.withyou.platform.compare;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jeremy.zhao
 */
public class FieldCompare {

    private String name;
    private String age;

    public Map<String, List<Object>> compare(FieldCompare origin, FieldCompare current, String ...fields) throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        PropertyDescriptor[] pds = Introspector.getBeanInfo(origin.getClass()).getPropertyDescriptors();
        Map<String, List<Object>> identity = new HashMap<>(fields.length);
        for (String f: fields) {
            for (PropertyDescriptor p: pds) {
                String name = p.getName();
                if (f.equals(name)) {
                    Method getter = p.getReadMethod();
                    Object oldValue = getter.invoke(origin);
                    Object newValue = getter.invoke(current);
                    if (null == oldValue && null ==  newValue) {
                        continue;
                    }
                    boolean notEqual = null != oldValue && !oldValue.equals(newValue) || !newValue.equals(oldValue);
                    if (notEqual) {
                        identity.put(name, Arrays.asList(oldValue, newValue));
                    }
                }
            }
        }
        return identity;
    }
}
