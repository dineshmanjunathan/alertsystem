package com.ss.app.vo;

public class CategoryVo {

	private String code;
	private String description;
	private long activedays;
	private Double rewardPoint;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getActivedays() {
		return activedays;
	}

	public void setActivedays(long activedays) {
		this.activedays = activedays;
	}

	public Double getRewardPoint() {
		return rewardPoint;
	}

	public void setRewardPoint(Double rewardPoint) {
		this.rewardPoint = rewardPoint;
	}

}
