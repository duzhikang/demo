package com.rudy.entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class User {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<? extends Class> aClass = User.class.getClass();
        Package aPackage = aClass.getPackage();
        System.out.println(aPackage.getName());
        Class<?> clazz = Class.forName("com.rudy.entity.User");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }
        // 反射创建对象
        try {
            User o = (User) clazz.newInstance();
            System.out.println(o);
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(null);
            User o1 = (User) declaredConstructor.newInstance();
            System.out.println(o1);
            Method method = clazz.getDeclaredMethod("setName", String.class);
            Object fy = method.invoke(o, "fy");
            System.out.println(fy);
            System.out.println(o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
