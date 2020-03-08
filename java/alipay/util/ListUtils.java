package com.pepay.api.alipay.util;

import java.util.HashSet;
import java.util.List;

/**
 * Create by on 2020/3/4.
 */
public class ListUtils {

    /**使用HashSet实现List去重(无序)
     *
     * @param list<?>
     * */
    public static List<?> removeDuplicationByHashSet(List<?> list) {
        HashSet set = new HashSet(list);
        //把List集合所有元素清空
        list.clear();
        //把HashSet对象添加至List集合
        list.addAll(set);
        return list;
    }
}
