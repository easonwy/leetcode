package org.github.ewu.proxy.annon;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ewu
 * @date 2021-05-06 下午 2:48
 **/
public class AnnoInjection {

    public static Object getBean(Object obj) {
        try {
            // 获取类属性
            Field f[] = obj.getClass().getDeclaredFields();
            for (Field ff : f) {
                // 获得属性上的注解
                Seven s = ff.getAnnotation(Seven.class);
                if (s != null) {
                    System.err.println("注入" + ff.getName() + "属性" + "tt" + s.value());
                    // 反射调用public set方法,如果为访问级别private,那么可以直接使用属性的set(obj,
                    // value);
                    obj.getClass()
                            .getMethod("set" + ff.getName().substring(0, 1).toUpperCase() + ff.getName().substring(1),
                                    new Class[]{String.class})
                            .invoke(obj, s.value());
                }
            }

            // 获得所有方法
            Method m[] = obj.getClass().getDeclaredMethods();
            for (Method mm : m) {
                // 获得方法注解
                Seven s = mm.getAnnotation(Seven.class);
                if (s != null) {
                    System.err.println("注入" + mm.getName() + "方法" + "t" + s.Property());
                    mm.invoke(obj, s.Property());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
