package com.yang.test.java.minio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidArgumentException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.errors.NoResponseException;
import io.minio.errors.RegionConflictException;

public class MinIO {
	public static void main(String[] args) throws InvalidEndpointException, InvalidPortException, InvalidKeyException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, NoResponseException, ErrorResponseException, InternalException, IOException, XmlPullParserException, RegionConflictException, InvalidArgumentException {
		String bucketName = "asiatrip";
		
		MinioClient minioClient = new MinioClient("http://192.168.8.70:9000", "AKIAIOSFODNN7EXAMPLE", "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");

		boolean isExist = minioClient.bucketExists(bucketName);
		if (isExist) {
			System.out.println("Bucket already exists.");
		} else {
			System.out.println("Bucket not exists.");
			minioClient.makeBucket(bucketName);
		}
		
		//minioClient.putObject(bucketName, "test", "/test.zip");
		
		
		InputStream is = minioClient.getObject(bucketName, "test");

		File target = new File("/test2.zip");
		FileOutputStream fileOutStream = new FileOutputStream(target);
		byte[] buffer = new byte[1024];
		while (is.read(buffer) != -1) {
			fileOutStream.write(buffer);
			
			buffer = new byte[1024];
		}
		
		fileOutStream.close();
	}
}