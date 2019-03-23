package com.architect.util;

import com.architect.entity.QrCodeInfo;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jiawx
 * @date 2019/3/18
 */
public class Guava {
    public static void main(String[] args) throws ParseException {
        long startTime = System.currentTimeMillis();
        QrCodeInfo info = new QrCodeInfo();
        info.setQrCodeUrl("http://skynet.ziroom.com/info/001234hd2d3jk4snui3k100001");
        info.setBatchNumber("PC201903111160011120");
        info.setGoodsCode("PZ00010020");
        info.setProductionPlant("我是生产厂名称-我是生产厂名称");
        info.setProductionTime(DateUtils.parseDate("2019-04-16", "yyyy-MM-dd"));
        QrCodeInfo info2 = new QrCodeInfo();
        info2.setQrCodeUrl("http://skynet.ziroom.com/info/001234hd2d3jk4snui3k100001");
        info2.setBatchNumber("1");
        info2.setGoodsCode("PZ00010020");
        info2.setProductionPlant("我是生产厂名称-我是生产厂名称");
        info2.setProductionTime(DateUtils.parseDate("2019-04-16", "yyyy-MM-dd"));
        QrCodeInfo info3 = new QrCodeInfo();
        info3.setQrCodeUrl("http://skynet.ziroom.com/info/001234hd2d3jk4snui3k100002");
        info3.setBatchNumber("PC201903111160011120");
        info3.setGoodsCode("PZ00010020");
        info3.setProductionPlant("我是生产厂名称-我是生产厂名称");
        info3.setProductionTime(DateUtils.parseDate("2019-04-16", "yyyy-MM-dd"));

        List<QrCodeInfo> infos = Lists.newArrayList();
        infos.add(info);
        infos.add(info2);
        infos.add(info3);

//        QrCodeInfo info4;
//        for (int i = 0; i < 200000; i++) {
//            info4 = new QrCodeInfo();
//            info4.setBatchNumber("PC201903111160011120");
//            info4.setGoodsCode("PZ00010020");
//            info4.setProductionPlant("我是生产厂名称-我是生产厂名称");
//            info4.setProductionTime(DateUtils.parseDate("2019-04-16", "yyyy-MM-dd"));
//            info4.setQrCodeUrl("http://skynet.ziroom.com/info/001234hd2d3jk4snui3k100003" + i);
//            infos.add(info4);
//        }

        Map<String, QrCodeInfo> qrCodeMap = infos.stream()
                .collect(Collectors.toMap(QrCodeInfo::getQrCodeUrl, Function.identity(),
                        (oldValue, newValue) -> newValue));

        List<String> qrCodes = infos
                .stream()
                .map(QrCodeInfo::getQrCodeUrl)
                .collect(Collectors.toList());

        List<String> qrCodeUrls = new ArrayList<>();
        for (Multiset.Entry<String> entry : HashMultiset.create(qrCodes).entrySet()) {
            if (entry.getCount() > 1) {
                qrCodeUrls.add(entry.getElement());
            }
        }
        List<QrCodeInfo> repeatQrCodeInfos = Lists.newArrayList();
        QrCodeInfo qrCodeInfo;
        for (String qrCodeUrl : qrCodeUrls) {
            qrCodeInfo = qrCodeMap.get(qrCodeUrl);
            if (qrCodeInfo != null) {
                qrCodeMap.remove(qrCodeUrl, qrCodeInfo);
                repeatQrCodeInfos.add(qrCodeInfo);
            }
        }
        Collection<QrCodeInfo> collection = qrCodeMap.values();
        for (QrCodeInfo codeInfo : collection) {
            System.out.println("业务处理...");
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
        System.out.println(repeatQrCodeInfos);
    }
}
