package org.github.ewu.proxy;

import org.github.ewu.proxy.annon.AnnoInjection;
import org.github.ewu.proxy.impl.AOPMethod;

import java.lang.reflect.Proxy;

/**
 * @author ewu
 * @date 2021-05-06 下午 4:06
 **/
public class AnimalFactory {

    /***
     * 获取对象方法
     * @param obj
     * @return
     */
    private static Object getAnimalBase(Object obj,AOPMethod method){
        //获取代理对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new AOPHandle(AnnoInjection.getBean(obj),method));
    }

    /***
     * 获取对象方法
     * @param obj
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAnimal(Object obj,AOPMethod aopMethod){
        return (T) getAnimalBase(obj,aopMethod);
    }
    /***
     * 获取对象方法
     * @param className
     * @return
     */
    @SuppressWarnings("unchecked")
    public static  <T> T getAnimal(String className, AOPMethod method){
        Object obj=null;
        try {
            obj= getAnimalBase(Class.forName(className).newInstance(),method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)obj;
    }

    /***
     * 获取对象方法
     * @param clz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static  <T> T  getAnimal(Class<?> clz,AOPMethod method){
        Object obj=null;
        try {
            obj= getAnimalBase(clz.newInstance(),method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)obj;
    }

}
