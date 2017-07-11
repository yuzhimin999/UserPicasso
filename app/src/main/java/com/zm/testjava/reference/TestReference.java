package com.zm.testjava.reference;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by yuzhimin on 17-7-5.
 */
public class TestReference {
    //创建一个引用队列
    private static ReferenceQueue<RefObject> rq = new ReferenceQueue<RefObject>();
    private static int size = 10;
    /**
     * 验证队列
     */
    public static void checkQueue(){
        Reference< RefObject> ref = (Reference<RefObject>) rq.poll();
        if(null != ref)
            System.out.println("In queue: "+ref+" : "+ref.get());
    }

    /**
     * 创建10个RefObject对象\以及10个软引用
     */
    public static void testSoftReference(){
        System.out.println("--------执行软引用开始-------");
        Set<SoftReference<RefObject>> sa = new HashSet<SoftReference<RefObject>>();
        for(int i = 0; i < size; i++) {
            SoftReference<RefObject> ref= new SoftReference<RefObject>(new RefObject("SoftReference " + i), rq);
            System.out.println("Just created: " +ref.get());
            sa.add(ref);
        }
        System.gc();
        checkQueue();
        System.out.println("--------执行软引用结束-------");
    }

    /**
     * 创建10个Grocery对象以及10个弱引用
     */
    public static void testWeakReference(){
        System.out.println("--------执行弱引用开始-------");
        Set<WeakReference<RefObject>> wa = new HashSet<WeakReference<RefObject>>();
        for(int i = 0; i < size; i++) {
            WeakReference<RefObject> ref=new WeakReference<RefObject>(new RefObject("WeakReference " + i), rq);
            System.out.println("Just created: " +ref.get());
            wa.add(ref);
        }
        System.gc();
        checkQueue();
        System.out.println("--------执行软弱用结束-------");
    }

    /**
     * 创建10个Grocery对象以及10个虚引用
     */
    public static void testPhantomReference(){
        System.out.println("--------执行虚引用开始-------");
        Set<PhantomReference<RefObject>> pa = new HashSet<PhantomReference<RefObject>>();
        for(int i = 0; i < size; i++) {
            PhantomReference<RefObject>ref =
                    new PhantomReference<RefObject>(new RefObject("PhantomReference " + i), rq);
            System.out.println("Just created: " +ref.get());
            pa.add(ref);
        }
        System.gc();
        checkQueue();
        System.out.println("--------执行虚弱用结束-------");


    }
    public static void Test(){
        testSoftReference();
        testWeakReference();
        testPhantomReference();
    }
}
