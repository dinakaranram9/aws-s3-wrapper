package com.dinakaran.awss3wrapper.upload;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.dinakaran.awss3wrapper.domain.S3File;

/*Interface for S3upload and S3download
* @author Dinakaran Ramadurai
* @since 2.0
*/
public interface S3Client {

	public String uploadFile(MultipartFile file) throws AmazonServiceException, SdkClientException, IOException;
	
	public S3File downloadFile(String path);

}
