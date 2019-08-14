/**
 * 
 */
package com.lingfeng.pets.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 谷春
 *
 */
public class test {
    
       
    public static void main(String[] args) {
        /*
         * List<Integer> list = Arrays.asList(1,2,3,4,5,6); List<Integer> stream
         * = list.stream().filter( i -> i%2 == 0).collect(Collectors.toList());
         * // System.out.print(stream);
         * 
         * List<Integer> list4 = Arrays.asList(1, 2, 3, 4, 5, 6); List<Integer>
         * collect = list4.stream().map(i -> i *
         * 2).collect(Collectors.toList()); System.out.println(collect);
         */
        
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"张三", "李四"});
        list.add(new String[]{"王五", "张三"});
        list.add(new String[]{"钱七", "周八"});
        
        List<String> list3 = list.stream().flatMap( s -> 
        Arrays.stream(s)).collect(Collectors.toList());
        System.out.println(list.toArray()+"--------"+list3);
    }
}
