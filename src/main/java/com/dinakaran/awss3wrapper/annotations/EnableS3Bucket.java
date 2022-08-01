package com.dinakaran.awss3wrapper.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.dinakaran.awss3wrapper.configurations.S3BucketConfigurations;
import com.dinakaran.awss3wrapper.uploadImpl.S3ClientImpl;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({S3BucketConfigurations.class, S3ClientImpl.class})
public @interface EnableS3Bucket {

}
