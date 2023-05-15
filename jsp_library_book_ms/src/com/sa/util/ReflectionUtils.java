// 
// 
// 

package com.sa.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;

public class ReflectionUtils
{
    public static Class getSuperClassGenricType(final Class clazz, final int index) {
        final Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        final Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class)params[index];
    }
    
    public static <T> Class<T> getSuperGenericType(final Class clazz) {
        return (Class<T>)getSuperClassGenricType(clazz, 0);
    }
    
    public static Method getDeclaredMethod(final Object object, final String methodName, final Class<?>[] parameterTypes) {
        Class<?> superClass = object.getClass();
        while (superClass != Object.class) {
            try {
                return superClass.getDeclaredMethod(methodName, parameterTypes);
            }
            catch (NoSuchMethodException ex) {
                superClass = superClass.getSuperclass();
            }
        }
        return null;
    }
    
    public static void makeAccessible(final Field field) {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
    }
    
    public static Field getDeclaredField(final Object object, final String filedName) {
        Class<?> superClass = object.getClass();
        while (superClass != Object.class) {
            try {
                return superClass.getDeclaredField(filedName);
            }
            catch (NoSuchFieldException ex) {
                superClass = superClass.getSuperclass();
            }
        }
        return null;
    }
    
    public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes, final Object[] parameters) throws InvocationTargetException {
        final Method method = getDeclaredMethod(object, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
        }
        method.setAccessible(true);
        try {
            return method.invoke(object, parameters);
        }
        catch (IllegalAccessException e) {
            System.out.println("\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u951f\u65a4\u62f7\u951f\u9636\u7b79\u62f7\u951f\u65a4\u62f7\u951f\u5c4a\u5e38");
            return null;
        }
    }
    
    public static void setFieldValue(final Object object, final String fieldName, final Object value) {
        final Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }
        makeAccessible(field);
        try {
            field.set(object, value);
        }
        catch (IllegalAccessException e) {
            System.out.println("\u6d93\u5d85\u5f72\u9473\u82a5\u59cf\u9351\u8679\u6b91\u5bee\u509a\u7236");
        }
    }
    
    public static Object getFieldValue(final Object object, final String fieldName) {
        final Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }
        makeAccessible(field);
        Object result = null;
        try {
            result = field.get(object);
        }
        catch (IllegalAccessException e) {
            System.out.println("\u6d93\u5d85\u5f72\u9473\u82a5\u59cf\u9351\u8679\u6b91\u5bee\u509a\u7236");
        }
        return result;
    }
}
