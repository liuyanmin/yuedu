package com.kingsoft.utils;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by SHIZHIDA on 2017/3/30.
 */
public class StringUtils {

    public static boolean in(String str,String... match){
        return ArrayUtils.contains(match,str);
    }

    /**
     * 随机生成一个字符串，1个字母+3个数字，不能出现字母L和l
     * @param length
     * @return
     */
    public static String getRandomExchangeCode(int length) { //length表示生成字符串的长度
        String baseChar = "abcdefghijkmnopqrstuvwxyzABCDEFGHIJKMNOPQRSTUVWXYZ";
        String baseNum = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        // 保存字符数
        List<Character> nums = new ArrayList<>();
        // 随机产生一个字母
        int index = random.nextInt(baseChar.length());
        char c = baseChar.charAt(index);
        nums.add(c);

        // 随机产生三个数字
        for (int i = 0; i < length - 1; i++) {
            int number = random.nextInt(baseNum.length());
            nums.add(baseNum.charAt(number));
        }
        //打乱顺序
        Collections.shuffle(nums);
        nums.forEach(num -> sb.append(num));

        return sb.toString();
    }

    public static void main(String args[]) {
        String str = getRandomExchangeCode(4);
        System.out.println(str);
    }

}
