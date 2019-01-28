package com.example.demo.util;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wjw on 2016/11/15.
 * @author wjw
 */
@Component
public class SnowFlakeKeyGen implements EnvironmentAware {

    private static final long workerIdBits = 6L;

    private static final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    private static final long sequenceBits = 6L;

    private static final long workerIdShift = 6L;

    private static final long timestampLeftShift = sequenceBits + workerIdBits;

    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long workerId;

    private long sequence = 0L;

    private long lastTimestamp = -1L;

    private final static long twepoch = 1452866247339L;
    
    

    public SnowFlakeKeyGen()
    {
        if(workerId > maxWorkerId || workerId < 0)
        {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
    }
    
    @Override
    public void setEnvironment(Environment environment)
    {
        workerId= NumberUtils.toLong(environment.getProperty("workerId"));
        if(workerId > maxWorkerId || workerId < 0)
        {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
    }

    public SnowFlakeKeyGen(long workerId)
    {
        if(workerId > maxWorkerId || workerId < 0)
        {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId=workerId;
    }

    public synchronized long nextId()
    {
        long timestamp = timeGen();
        if(timestamp < lastTimestamp)
        {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        if(lastTimestamp == timestamp)
        {
            sequence = (sequence + 1) & sequenceMask;
            if(sequence == 0)
            {
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        else
        {
            sequence = timestamp % 9;
        }

        lastTimestamp = timestamp;
        return (timestamp - twepoch << timestampLeftShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp)
    {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp)
        {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen()
    {
        return System.currentTimeMillis();
    }
    
    public static long minId(Date date){
        return (date.getTime() - twepoch << timestampLeftShift) | (0 << workerIdShift) | 0;
    }
    
    public static long maxId(Date date){
        int w=(-1 ^ (-1 << workerIdBits))<<workerIdShift;
        int s=(-1 ^ (-1 << sequenceBits));
        return (date.getTime() - twepoch << timestampLeftShift) | w | s;
    }
    
    public static Date getDate(long id){
        return new Date((id>> timestampLeftShift) + twepoch );
    }
    public static Date getSimpleDate(long id){
        return new Date(((id>> timestampLeftShift) + twepoch)/1000*1000);
    }
    public static String getSimpleString(long id){
        java.text.SimpleDateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date(((id>> timestampLeftShift) + twepoch)/1000*1000));
    }
}
