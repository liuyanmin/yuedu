package com.kingsoft.utils.version;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by YANGFENG3 on 2016/1/6.
 */
public class VersionCompare {
    public static final int GREATER_THAN = 1;
    public static final int EQUALS = 0;
    public static final int LESS_THAN = -1;


    public static boolean eq(String v1,String v2){
        return versionCompare(v1,v2)==EQUALS;
    }
    public static boolean lt(String v1,String v2){
        return versionCompare(v1,v2) == LESS_THAN;
    }
    public static boolean lte(String v1,String v2){
        return lt(v1,v2) || eq(v1,v2);
    }
    public static boolean gt(String v1,String v2){
        return versionCompare(v1,v2)==GREATER_THAN;
    }
    public static boolean gte(String v1,String v2){
        return gt(v1,v2)||eq(v1,v2);
    }

    public static <T> List<T> filter(String currentVersion,List<T> data,MaxVersionGetter<T> max,MinVersionGetter<T> min){
        return data.stream().filter(a->
            (StringUtils.isEmpty(max.getMax(a)) || lte(currentVersion,max.getMax(a)))
                    && (StringUtils.isEmpty(min.getMin(a)) || gte(currentVersion,min.getMin(a)))
        ).collect(Collectors.toList());
    }

    /**
     * 功能：判断version1和version2的大小
     * version1 > version2 返回 1
     * version1 = version2 返回 0
     * version1 < version2 返回-1
     * 注意：如果版本 (version1 == null) || (version1 == "") 返回 -1
     * 如果版本 (version2 == null) || (version2 == "") 返回 1
     * 两者都空，则version1优先;
     */
    public static int versionCompare(String version1, String version2) {
        if (StringUtils.isEmpty(version1) && StringUtils.isEmpty(version2)){
            return EQUALS;
        }

        if ((version1 == null) || (version1.length() == 0)) {
            return LESS_THAN;
        }
        if ((version2 == null) || (version2.length() == 0)) {
            return GREATER_THAN;
        }
        if (version1.equals(version2)) {
            return EQUALS;
        }

        List<Integer> versionList1 = splitVersion(version1);
        //如果version1格式不对，或者versionList1返回空，则返回version1 < version2
        if (versionList1 == null || versionList1.size() == 0) {
            return LESS_THAN;
        }
        List<Integer> versionList2 = splitVersion(version2);
        //如果version2格式不对，或者versionList2返回空，则返回version2 < version1
        if (versionList2 == null || versionList2.size() == 0) {
            return GREATER_THAN;
        }

        return versionListCompare(versionList1, versionList2);

    }

    /**
     * 功能：判断版本大小，版本已经切分为整型数组
     * 注意：1，公共前缀，版本较长的版本更大 8.3.0  > 8.3
     */
    private static int versionListCompare(List<Integer> versionList1, List<Integer> versionList2) {
        int size1 = versionList1.size();
        int size2 = versionList2.size();
        int size = size1 < size2 ? size1 : size2; //min

        for (int i = 0; i < size; i++) {
            int subVersion1 = versionList1.get(i), subVersion2 = versionList2.get(i);
            if (subVersion1 != subVersion2) {
                return subVersion1 > subVersion2 ? GREATER_THAN : LESS_THAN;
            }
        }
        //8.3.1 > 8.3
        return size1 == size2 ? EQUALS : ((size1 > size2) ? GREATER_THAN : LESS_THAN);
    }

    /**
     * 功能：将版本切分成List<Integer>
     * 如果出现问题，返回空
     * 问题包括：1,空版本； 8..8
     * 2,字符错误；8.a.8
     */
    private static List<Integer> splitVersion(String version) {
        String[] versionSegments = version.split("\\.");
        List<Integer> versionList = new ArrayList<>();
        for (String versionSegment : versionSegments) {
            if (StringUtils.isEmpty(versionSegment)) {
                return null;
            }
            try {
                versionList.add(Integer.parseInt(versionSegment));
            } catch (Exception e) {
                return null;
            }
        }
        return versionList;
    }
}
