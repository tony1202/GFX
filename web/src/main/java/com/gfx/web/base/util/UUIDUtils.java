package com.gfx.web.base.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.RandomBasedGenerator;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.mchange.lang.IntegerUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author tony
 * @date 2018/9/5
 * @Description: 生成uuid字符串
 */
public class UUIDUtils {

    private static TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
    private static RandomBasedGenerator randomBasedGenerator = Generators.randomBasedGenerator();

    /**
     * 基于时间的uuid
     * @return
     */
    public static String timeUUID(){
        return timeBasedGenerator.generate().toString();
    }

    /**
     * 随机UUID
     * @return
     */
    public static String randomUUID(){
        return randomBasedGenerator.generate().toString();
    }

    /**
     * 默认使用timeuuid
     * @return
     */
    public static String uuid(){
        return timeUUID().contains("-")?timeUUID().replaceAll("-",""):timeUUID();
    }

    /**
     * 指定前缀+日期+5位后缀
     * @param pre 前缀
     * @return
     */
    public static String uuid(String pre){
        String result = null;
        try {
            Random random = new Random(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Thread.sleep(5);
            DecimalFormat df = new DecimalFormat("0000");
            int i = random.nextInt(10000);
            String format = df.format(i);
            result = pre+dateFormat.format(new Date())+format;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

}
