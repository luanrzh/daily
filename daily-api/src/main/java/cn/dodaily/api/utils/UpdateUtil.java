package cn.dodaily.api.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * 更新操作前置处理工具类
 * 使用场景：前端发出更新请求时，仅提供了部分字段，为便于数据库更新操作，需要把剩余的字段补齐。
 * 两种方式：
 * (1) 从数据库中获取旧对象-->如果新对象某个属性值为空-->将旧对象的值赋给新对象-->循环第二步第三步-->新"新对象"用于数据库更新操作
 * (2) 从数据库中获取旧对象-->如果新对象某个属性值非空-->将新对象的值赋给旧对象-->循环第二步第三步-->新"旧对象"用于数据库更新操作
 * --------------------------
 * 示例：
 * new   : {1 , null , 4}
 * old   : {1 ,  2   , 3}
 * result: {1 ,  2   , 4}
 * -------------------------
 */
public class UpdateUtil {
    /**
     * 方式一：将旧对象中的值赋给新对象中值为空的属性
     *
     * @param newObject 新的不完整的对象
     * @param oldObject 旧的完整对象
     */
    public static Object fillNullToNew(Object newObject, Object oldObject) {
        //便利所有属性
        for (Field field : newObject.getClass().getDeclaredFields()) {
            try {
                //将私有属性设置为可访问
                field.setAccessible(true);
                //获取属性名
                String fieldName = field.getName();
                //获取属性值
                Object fieldValue = field.get(newObject);
                //如果新对象中的属性值为空，则将旧对象中的值赋予新对象
                if (fieldValue == null) {
                    //属性名首字母转换成大写（content --> Content）
                    String ucFileName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    //根据属性名获取get方法名(Content --> getContent)
                    String getMethodString = "get" + ucFileName;
                    //根据属性名获取set方法名(Content --> setContent)
                    String setMethodString = "set" + ucFileName;
                    //获取get方法对象
                    Method getMethod = oldObject.getClass().getMethod(getMethodString);
                    //获取set方法对象
                    Method setMethod = newObject.getClass().getMethod(setMethodString, field.getType());
                    //执行get方法，获取旧对象的属性值
                    Object oldFieldValue = getMethod.invoke(oldObject);
                    //执行set方法，将新对象的属性值更新
                    setMethod.invoke(newObject, oldFieldValue);
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return newObject;
    }

    /**
     * 方式二：将新对象中的非空属性赋给旧对象
     * 参考自：https://www.jianshu.com/p/d6424d98b02e
     *
     * @param newObject 新的不完整的对象
     * @param oldObject 旧的完整对象
     */
    public static Object fillNotNullToOld(Object newObject, Object oldObject) {
        String[] nullPropertyNames = getNullPropertyNames(newObject);
        BeanUtils.copyProperties(newObject, oldObject, nullPropertyNames);
        return oldObject;
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    /**
     * 判断两个对象的属性值是否完全相同
     *
     * @param object1 对象一
     * @param object2 对象而
     * @return 是否相同
     */
    public static boolean isObjectEqual(Object object1, Object object2) {
        //便利所有属性
        for (Field field : object1.getClass().getDeclaredFields()) {
            try {
                //将私有属性设置为可访问
                field.setAccessible(true);
                //获取属性名
                String fieldName = field.getName();
                //获取属性值
                Object fieldValue1 = field.get(object1);
                Object fieldValue2 = field.get(object2);
                if (!fieldValue1.equals(fieldValue2)) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
