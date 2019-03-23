package com.architect.util;

import com.architect.entity.QrCodeInfo;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jiawx
 * @date 2019/3/18
 */
public class Java8 {
    public static List<String> getDuplicateElements(List<QrCodeInfo> list) {
        // list 对应的 Stream
        return list.stream()
                // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .collect(Collectors.toMap(e -> e.getQrCodeUrl(), e -> 1, Integer::sum))
                // 所有 entry 对应的 Stream
                .entrySet().stream()
                // 过滤出元素出现次数大于 1 的 entry
                .filter(entry -> entry.getValue() > 1)
                // 获得 entry 的键（重复元素）对应的 Stream
                .map(Map.Entry::getKey)
                // 转化为 List
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        QrCodeInfo info = new QrCodeInfo();
        info.setQrCodeUrl("http://skynet.ziroom.com/info/001234hd2d3jk4snui3k100001");
        info.setBatchNumber("1");
        QrCodeInfo info2 = new QrCodeInfo();
        info2.setQrCodeUrl("http://skynet.ziroom.com/info/001234hd2d3jk4snui3k100001");
        info2.setBatchNumber("2");
        QrCodeInfo info3 = new QrCodeInfo();
        info3.setQrCodeUrl("http://skynet.ziroom.com/info/001234hd2d3jk4snui3k100002");
        info2.setBatchNumber("2");

        List<QrCodeInfo> infos = Lists.newArrayList();
        infos.add(info);
        infos.add(info2);
        infos.add(info3);
        long startTime = System.currentTimeMillis();
        List<String> duplicate = getDuplicateElements(infos);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println("list 中重复的元素：" + duplicate);
    }
}
