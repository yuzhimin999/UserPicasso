package com.zm.testjava.enumtest;

/**
 * Created by yuzhimin on 17-7-6.
 */

@SuppressWarnings("PointlessBitwiseExpression")
public enum MemoryPolicyTest {

    /** Skips memory cache lookup when processing a request. */
    NO_CACHE(1 << 0),
    /**
     * Skips storing the final result into memory cache. Useful for one-off requests
     * to avoid evicting other bitmaps from the cache.
     */
    NO_STORE(1 << 1);

    public static boolean shouldReadFromMemoryCache(int memoryPolicy) {
        return (memoryPolicy & MemoryPolicyTest.NO_CACHE.index) == 0;
    }

    public static boolean shouldWriteToMemoryCache(int memoryPolicy) {
        return (memoryPolicy & MemoryPolicyTest.NO_STORE.index) == 0;
    }

    final int index;

    MemoryPolicyTest(int index) {
        this.index = index;
    }
}