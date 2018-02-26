package com.weixin.vo;

public class Role {
	private Integer rid;
	private String title;
	private String flag;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Role [rid=" + rid + ", title=" + title + ", flag=" + flag + "]";
	}
	
	
}
