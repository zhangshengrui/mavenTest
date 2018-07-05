package odinWhiteList;

import cn.gy4j.frame.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Startup
 * @version 1.0
 * @author
 */
public class Startup extends DataEntity<Startup> implements Cloneable{

	// 主键
	private Integer id;
	// 服务器ip
	private String serverIp;
	// 大类名称
	private String mainName;
	// 子类名称
	private String subName;
	// 应用名称
	private String appName;
	// 启动项名称
	private String startupName;
	// 启动项描述
	private String os;
	// 中心应用负责人
	private String manager;


	// 检测时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	private Date findTime;
	// 0 非异常，1 异常
	private Integer status;
	// 修复时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	private Date repairTime;
	// 异常简报
	private String message;
	// 已添加白名单类型（0：未添加 1：基础白名单 2业务白名单 3单机白名单）
	private Integer whiteListFlag;

	private String operator;

    private String startTimeFlag;
    private String endTimeFlag;

	private String text;

	private Integer searchTime;								//查询开始时间
	
	
	
	public Integer getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(Integer searchTime) {
		this.searchTime = searchTime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getStartTimeFlag() {
        return startTimeFlag;
    }

    public void setStartTimeFlag(String startTimeFlag) {
        this.startTimeFlag = startTimeFlag;
    }

    public String getEndTimeFlag() {
        return endTimeFlag;
    }

    public void setEndTimeFlag(String endTimeFlag) {
        this.endTimeFlag = endTimeFlag;
    }

    public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setServerIp(String value) {
		this.serverIp = value;
	}

	public String getServerIp() {
		return this.serverIp;
	}

	public void setMainName(String value) {
		this.mainName = value;
	}

	public String getMainName() {
		return this.mainName;
	}

	public void setSubName(String value) {
		this.subName = value;
	}

	public String getSubName() {
		return this.subName;
	}

	public void setAppName(String value) {
		this.appName = value;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setStartupName(String value) {
		this.startupName = value;
	}

	public String getStartupName() {
		return this.startupName;
	}

	public void setOs(String value) {
		this.os = value;
	}

	public String getOs() {
		return this.os;
	}

	public void setManager(String value) {
		this.manager = value;
	}

	public String getManager() {
		return this.manager;
	}

	public void setFindTime(Date value) {
		this.findTime = value;
	}

	public Date getFindTime() {
		return this.findTime;
	}

	public void setStatus(Integer value) {
		this.status = value;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setRepairTime(Date value) {
		this.repairTime = value;
	}

	public Date getRepairTime() {
		return this.repairTime;
	}

	public void setMessage(String value) {
		this.message = value;
	}

	public String getMessage() {
		return this.message;
	}

	public void setWhiteListFlag(Integer value) {
		this.whiteListFlag = value;
	}

	public Integer getWhiteListFlag() {
		return this.whiteListFlag;
	}

	@JsonIgnore
	public Startup fetchClone() {
        try {
            return this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
	public String toString() {
		return "Startup [id=" + id + ", serverIp=" + serverIp + ", mainName=" + mainName + ", subName=" + subName
				+ ", appName=" + appName + ", startupName=" + startupName + ", os=" + os + ", manager=" + manager
				+ ", findTime=" + findTime + ", status=" + status + ", repairTime=" + repairTime + ", message="
				+ message + ", whiteListFlag=" + whiteListFlag + ", operator=" + operator + ", startTimeFlag="
				+ startTimeFlag + ", endTimeFlag=" + endTimeFlag + "]";
	}

	@Override
    protected Startup clone() throws CloneNotSupportedException {
        return (Startup) super.clone();
    }
}
