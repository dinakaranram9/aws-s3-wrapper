package com.dinakaran.awss3wrapper.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/*Configuration class to connect to s3bucket and create bean for
 * AmazonS3 object 
* @author Dinakaran Ramadurai
* @see AmazonS3.class
* @since 2.0
*/
@Configuration
public class S3BucketConfigurations {

	@Value("${spring.s3.endpointUrl}")
	private String endpointUrl;
	@Value("${spring.s3.accessKey}")
	private String accessKey;
	@Value("${spring.s3.secret}")
	private String secretKey;

	public AWSCredentials credentials() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		return credentials;
	}

	@Bean
	public AmazonS3 amazonS3() {
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials()))
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointUrl, "default")).build();
		return s3client;
	}

}
