package com.architect.listener;

import com.architect.conf.Constants;
import com.architect.conf.ServerSelected;
import com.architect.conf.SystemManager;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * @author wenxiong.jia
 * @since 2018/12/6
 */
public class ServerSelectedProcess {
    /**
     * 解析服务选择器
     *
     * @param properties
     * @return
     */
    public static List<ServerSelected> parserServerSelected(Properties properties) {
        List<ServerSelected> serverSelecteds = new LinkedList<>();
        if (properties != null) {
            Enumeration<?> enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                String name = (String) enumeration.nextElement();
                if (name.endsWith(Constants.SELECTED_SERVER)) {
                    String serverName = name.substring(0, name.length() - Constants.SELECTED_SERVER.length() - 1);
                    String val = properties.getProperty(name);
                    SystemManager.getLogger().warn("init.server.selected->{}:{}", name, val);
                    serverSelecteds.add(new ServerSelected(serverName, val));
                }
            }
        }
        return serverSelecteds;
    }
}
