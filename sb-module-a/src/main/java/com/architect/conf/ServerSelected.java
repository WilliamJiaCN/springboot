package com.architect.conf;

/**
 * @author wenxiong.jia
 * @since 2018/12/6
 */
public class ServerSelected {
    // 服务名称
    private String serverName;
    // 选择指定服务分组；如果配置分组为“*”则匹配任意分组和缺省分组
    private String selectedGroup;

    public ServerSelected(String serverName, String selectedGroup) {
        if (serverName == null || selectedGroup == null) {
            System.err.println("服务选择-->>服务名称：" + serverName + " selectedGroup:" + selectedGroup);
        }
        // Assert.isNotNull(serverName, ErrorCode.ILLEGAL_ARGUMENT, "服务名称不能为空");
        // Assert.isNotNull(selectedGroup, ErrorCode.ILLEGAL_ARGUMENT,
        // "服务分组名称不能为空");
        this.serverName = serverName;
        if (selectedGroup != null) {
            this.selectedGroup = selectedGroup.trim();
        }
    }

    public String getServerName() {
        return serverName;
    }

    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    /**
     * 匹配分组
     *
     * @param group
     * @return
     */
    public boolean matchesGroup(String group) {
        if ("*".equals(this.selectedGroup)) {
            return true;
        } else {
            if (selectedGroup != null) {
                return this.selectedGroup.equalsIgnoreCase(group);
            } else {
                return false;
            }
        }
    }

    public boolean matchesServer(String appName) {
        return this.serverName.equalsIgnoreCase(appName);
    }
}
