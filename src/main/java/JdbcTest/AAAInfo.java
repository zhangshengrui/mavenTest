package JdbcTest;

import cn.gy4j.frame.persistence.DataEntity;

import java.io.Serializable;

public class AAAInfo extends DataEntity<AAAInfo> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     区组ID
     */
    private Integer distId;

    /**
     aaa
     */
    private String aaa;

    /**
     ip
     */
    private String ip;

    /**
     activate_aaa_ip
     */
    private String activate_aaa_ip;

    /**
     antibot_server
     */
    private String antibot_server;

    public Integer getDistId() {
        return distId;
    }

    public void setDistId(Integer distId) {
        this.distId = distId;
    }

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getActivate_aaa_ip() {
        return activate_aaa_ip;
    }

    public void setActivate_aaa_ip(String activate_aaa_ip) {
        this.activate_aaa_ip = activate_aaa_ip;
    }

    public String getAntibot_server() {
        return antibot_server;
    }

    public void setAntibot_server(String antibot_server) {
        this.antibot_server = antibot_server;
    }
}
