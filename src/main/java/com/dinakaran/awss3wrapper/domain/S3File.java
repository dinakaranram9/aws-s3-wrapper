package com.dinakaran.awss3wrapper.domain;

import java.io.InputStream;

import lombok.Data;
/*Contains model class used for downloading contents from S3 bucket
* @author Dinakaran Ramadurai
* @since 2.0
*/
@Data
public class S3File {
	String fileType;
	String fileName;
	Long fileSize;
	InputStream fileContent;

}
