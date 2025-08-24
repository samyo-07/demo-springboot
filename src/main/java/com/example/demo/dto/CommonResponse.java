package com.example.demo.dto;

import java.util.List;

public class CommonResponse {

	private List<?> records;

	public CommonResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommonResponse(List<?> records) {
		super();
		this.records = records;
	}

	public List<?> getRecords() {
		return records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "CommonResponse [records=" + records + "]";
	}

}
