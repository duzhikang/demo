package com.rudy.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapDemo {

    public static Map<String, String> map = new HashMap<>();
    static {
        map.put("a", "aaa");
        map.put("b", "baaa");
        map.put("c", "caaa");
        map.put("d", "daaa");
    }

    public static void main(String[] args) {
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String value = entry.getValue();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        Set<String> strings = map.keySet();
        for (String string : strings) {
            System.out.println(string);
            System.out.println(map.get(string));
        }
    }
}
