package odinWhiteList;

import cn.gy4j.frame.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class StartupWhiteList extends DataEntity<StartupWhiteList> {

	@Override
	public String toString() {
		return "StartupWhiteList [id=" + id + ", boot=" + boot + ", os=" + os + ", business=" + business + ", app="
				+ app + ", ip=" + ip + ", type=" + type + ", author=" + author + ", updateTime=" + updateTime
				+ ", memo=" + memo + ", addString=" + addString + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	private Integer id;
	// 启动项名称
	private String boot;
	// 系统名称
	private String os;
	// 业务名称
	private String business;
	// 应用名称
	private String app;
	// 服务器IP
	private String ip;
	// 白名单类型（1：基础，2：业务，3：单机）
	private Integer type;
	// 操作人
	private String author;
	// 操作时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	private Date updateTime;
	// 描述
	private String memo;
	
	//附加字段 
	//更改白名单类型
	private String addString;

	public String getAddString() {
		return addString;
	}

	public void setAddString(String addString) {
		this.addString = addString;
	}

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setBoot(String value) {
		this.boot = value;
	}

	public String getBoot() {
		return this.boot;
	}

	public void setOs(String value) {
		this.os = value;
	}

	public String getOs() {
		return this.os;
	}

	public void setBusiness(String value) {
		this.business = value;
	}

	public String getBusiness() {
		return this.business;
	}

	public void setApp(String value) {
		this.app = value;
	}

	public String getApp() {
		return this.app;
	}

	public void setIp(String value) {
		this.ip = value;
	}

	public String getIp() {
		return this.ip;
	}

	public void setType(Integer value) {
		this.type = value;
	}

	public Integer getType() {
		return this.type;
	}

	public void setAuthor(String value) {
		this.author = value;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setMemo(String value) {
		this.memo = value;
	}

	public String getMemo() {
		return this.memo;
	}

}
