package com.zm.testjava.reference;

/**
 * Created by yuzhimin on 17-7-5.
 */

public class RefObject {
    private static final int size = 80000;
    //创建一个Object数组，这样会占用很大的内存空间。预计大概会有640k左右的内存被占用
    private Object[] object = new Object[size];
    private String refId;

    public RefObject(String id) {
        this.refId = id;
    }

    public String RefIdToString() {
        return refId;
    }

    public void findName() {
        System.out.println("findName=" + refId);
    }
}
