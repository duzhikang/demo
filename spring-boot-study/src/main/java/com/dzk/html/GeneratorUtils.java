package com.dzk.html;/**
 * Created by dzk on 2020/3/17.
 */

/**
 * @ClassName GeneratorUtils
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/3/17
 **/
public class GeneratorUtils {

    public void generatorUtils() {
        String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
        System.out.println(classpath);


    }

    public static void main(String[] args) {
        new GeneratorUtils().generatorUtils();
    }
}
