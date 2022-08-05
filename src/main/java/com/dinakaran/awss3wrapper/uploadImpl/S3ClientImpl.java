package com.dinakaran.awss3wrapper.uploadImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.dinakaran.awss3wrapper.constants.S3Contants;
import com.dinakaran.awss3wrapper.domain.S3File;
import com.dinakaran.awss3wrapper.upload.S3Client;


public class S3ClientImpl implements S3Client {

	@Value("${spring.s3.env}")
	private String env;
	@Value("${spring.s3.applicationPath}")
	private String applicationPath;
	@Value("${spring.s3.bucketName}")
	private String bucketName;

	@Autowired
	private AmazonS3 amazonS3Client;
	
	/*Uploads the file to S3 bucket by taking file  
	* @author Dinakaran Ramadurai
	* @since 2.0
	*/
	public String uploadFile(MultipartFile file) throws AmazonServiceException, SdkClientException, IOException {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		metadata.setContentType(file.getContentType());
		metadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION);
		Map<String, String> metadataMap = new HashMap<>();
		metadataMap.put(S3Contants.FILE_NAME.getMsg(), file.getOriginalFilename());
		metadata.setUserMetadata(metadataMap);
		String keyName = generateFileKey(file.getOriginalFilename());
		amazonS3Client.putObject(bucketName, keyName, file.getInputStream(), metadata);
		return keyName;
	}
	
	/*generates unique file and path for each file 
	* @author Dinakaran Ramadurai
	* @since 2.0
	*/
	private String generateFileKey(String fileName) {
		StringBuffer key = new StringBuffer();
		key.append(applicationPath);
		key.append(env);
		key.append(fileName);
		key.append("_");
		key.append(LocalDateTime.now());
		Optional<String> extension = getExtensionByStringHandling(fileName);
		if (extension.isPresent()) {
			key.append(".");
			key.append(extension.get());
		}
		return key.toString();
	}

	private Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}
	
	/*downlads the file from S3 bucket by using the path specified
	* @author Dinakaran Ramadurai
	* @since 2.0
	*/
	@Override
	public S3File downloadFile(String path) {
		S3File s3File = new S3File();
		S3Object s3object = amazonS3Client.getObject(bucketName, path);
		s3File.setFileType(s3object.getObjectMetadata().getContentType());
		s3File.setFileName(s3object.getObjectMetadata().getUserMetaDataOf(S3Contants.FILE_NAME.getMsg()));
		s3File.setFileSize(s3object.getObjectMetadata().getContentLength());
		s3File.setFileContent(s3object.getObjectContent());
		return s3File;
	}

}
