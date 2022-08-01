package com.dinakaran.awss3wrapper.constants;

import lombok.Getter;

@Getter
public enum S3Contants {
	FILE_NAME("FILE_NAME", "FN-001"),
	EMPTY("", "");

	S3Contants(String msg, String code) {
		this.msg = msg;
		this.code = code;
	}

	private String msg;
	private String code;
}
