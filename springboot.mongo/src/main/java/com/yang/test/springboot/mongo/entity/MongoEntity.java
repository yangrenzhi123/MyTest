package com.yang.test.springboot.mongo.entity;

import org.springframework.data.annotation.Id;

public class MongoEntity {

	@Id
	private String _id;
	private String data;
	private String status;
	private String receiveTime;

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	@Override
	public String toString() {
		return "MongoEntity [_id=" + _id + ", data=" + data + ", status=" + status + ", receiveTime=" + receiveTime
				+ "]";
	}
}