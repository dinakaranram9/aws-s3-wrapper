package com.dinakaran.awss3wrapper.upload;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.dinakaran.awss3wrapper.domain.S3File;

public interface S3Client {

	public String uploadFile(MultipartFile file) throws AmazonServiceException, SdkClientException, IOException;
	
	public S3File downloadFile(String path);

}
