package com.dinakaran.awss3wrapper.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.dinakaran.awss3wrapper.configurations.S3BucketConfigurations;
import com.dinakaran.awss3wrapper.uploadImpl.S3ClientImpl;

/* Interface to create bean for S3BucketConfiguration class and 
 * S3ClientImpl
* @author Dinakaran Ramadurai
* @see S3BucketConfigurations.class
* @see S3ClientImpl.class
* @since 2.0
*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({S3BucketConfigurations.class, S3ClientImpl.class})
public @interface EnableS3Bucket {

}
