package com.yang.test.java.minio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.xmlpull.v1.XmlPullParserException;

import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.errors.NoResponseException;

public class App {
	public static void main(String[] args) throws InvalidEndpointException, InvalidPortException, InvalidKeyException, InvalidBucketNameException, NoSuchAlgorithmException, InsufficientDataException, NoResponseException, ErrorResponseException, InternalException, IOException, XmlPullParserException {
		MinioClient minioClient = new MinioClient("http://192.168.8.70:9000", "AKIAIOSFODNN7EXAMPLE", "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");

		boolean isExist = minioClient.bucketExists("asiatrip");
		if (isExist) {
			System.out.println("Bucket already exists.");
		} else {
			System.out.println("Bucket not exists.");
		}
	}
}