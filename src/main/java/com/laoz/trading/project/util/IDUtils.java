package com.laoz.trading.project.util;

import java.util.UUID;

/**
 * ID generation utility
 * ID 生成工具类
 */
public class IDUtils {

    private IDUtils() {
        // utility class, prevent instantiation
    }

    /**
     * Generate a 32-character UUID string (without hyphens)
     * 生成 32 位 UUID 字符串（去除连字符）
     *
     * @return 32-character UUID 32 位 UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
