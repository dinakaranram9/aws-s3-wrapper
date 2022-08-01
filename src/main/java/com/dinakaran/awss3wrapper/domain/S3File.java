package com.dinakaran.awss3wrapper.domain;

import java.io.InputStream;

import lombok.Data;

@Data
public class S3File {
	String fileType;
	String fileName;
	Long fileSize;
	InputStream fileContent;

}
