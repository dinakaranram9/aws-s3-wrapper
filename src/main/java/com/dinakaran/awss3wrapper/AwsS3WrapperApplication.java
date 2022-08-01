package com.dinakaran.awss3wrapper;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;

@SpringBootApplication
public class AwsS3WrapperApplication {

	public static void main(String[] args) throws AmazonServiceException, SdkClientException, IOException {
		SpringApplication.run(AwsS3WrapperApplication.class, args);
	}

}
