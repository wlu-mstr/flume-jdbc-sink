package com.wlu.flume.sink.domain;

public class TempData {
	private String timestamp;
	private double value;

	public TempData() {
	}

	public TempData(String timestamp, double value) {
		this.timestamp = timestamp;
		this.value = value;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
