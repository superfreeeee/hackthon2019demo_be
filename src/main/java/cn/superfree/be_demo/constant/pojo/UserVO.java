package cn.superfree.be_demo.constant.pojo;

public class UserVO {
    private static int counter = 0;

    private final int id;

    private String username;

    private String serverId;

    public UserVO() {
        id = ++counter;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
