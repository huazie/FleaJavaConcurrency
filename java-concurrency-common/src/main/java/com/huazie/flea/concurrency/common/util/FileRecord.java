package com.huazie.flea.concurrency.common.util;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 文件索引工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FileRecord {

    private static final Set<String> record = Collections.synchronizedSet(new HashSet<>());

    /**
     * 检查是否已经被索引
     *
     * @param file 待检查文件
     * @return true：已被索引，false：未被索引
     * @since 1.0.0
     */
    public static boolean alreadyIndexed(File file) {
        return record.contains(file.getAbsolutePath());
    }

    /**
     * 索引指定文件
     *
     * @param file 待索引文件
     * @since 1.0.0
     */
    public static void indexFile(File file) {
        record.add(file.getAbsolutePath());
    }
}
