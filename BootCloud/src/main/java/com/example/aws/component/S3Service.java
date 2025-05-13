package com.example.aws.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class S3Service {

    //버킷목록확인하기
    public void getBucketList() {

        //자격증명
        S3Client s3 = S3Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(ProfileCredentialsProvider.create()) //유저폴더/.aws/credentails파일을 읽음
                .build();

        //버킷목록
        ListBucketsResponse response = s3.listBuckets();
        List<Bucket> bucketList = response.buckets();
        for (Bucket bucket: bucketList) {
            System.out.println("Bucket name "+bucket.name());
        }
    }

    //버킷에 업로드하기
    public void uploadBucket(MultipartFile file) {

        String filename = file.getOriginalFilename();
        System.out.println( "한글: " + filename);

        //자격증명
        S3Client s3 = S3Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(ProfileCredentialsProvider.create()) //유저폴더/.aws/credentails파일을 읽음
                .build();

        String bucketName = "sws-project"; //버킷명

        try {
            Map<String, String> metadata = new HashMap<>(); //파일의 대한 정보
            metadata.put("author", "Mary Doe");
            metadata.put("version", "1.0.0.0");

            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName) //버킷명
                    .key(filename) //올릴파일명
                    .metadata(metadata) //메타데이터
                    .contentType(file.getContentType() ) //데이터에 대한 컨텐츠타입 (지정하지 않으면, 기본타입으로 지정됨)
                    .build();

            s3.putObject(putOb, RequestBody.fromBytes( file.getBytes() ) );
            System.out.println("Successfully placed " + filename + " into bucket " + bucketName);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            //System.exit(1);
        }

    }

    //버킷의 목록 가져오기
    public void list_bucket_objects() {

        //자격증명
        S3Client s3 = S3Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(ProfileCredentialsProvider.create()) //유저폴더/.aws/credentails파일을 읽음
                .build();

        String bucketName = "sws-project";

        try {
            ListObjectsV2Request listReq = ListObjectsV2Request.builder()
                    .bucket(bucketName)
                    .maxKeys(1)
                    .build();

            ListObjectsV2Iterable listRes = s3.listObjectsV2Paginator(listReq);

            for(S3Object content : listRes.contents() ) {
                System.out.println("Key: " + content.key() + " size = " + content.size());
            }

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }

    // 버킷 객체 삭제
    public void delete_bucket_object(List<String> bucket_obj_name){

        //자격증명
        S3Client s3 = S3Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .credentialsProvider(ProfileCredentialsProvider.create()) //유저폴더/.aws/credentails파일을 읽음
                .build();

        String bucketName = "sws-project";


        List<ObjectIdentifier> keys = new ArrayList<>();
        ObjectIdentifier objectId;

        for (int i = 0; i < bucket_obj_name.size(); i++) {
            String keyName = bucket_obj_name.get(i);
            objectId = ObjectIdentifier.builder()
                    .key(keyName)
                    .build();
            keys.add(objectId);
        }

        System.out.println(keys.size() + " objects successfully created.");

        // Delete multiple objects in one request.
        Delete del = Delete.builder()
                .objects(keys)
                .build();

        try {
            DeleteObjectsRequest multiObjectDeleteRequest = DeleteObjectsRequest.builder()
                    .bucket(bucketName)
                    .delete(del)
                    .build();

            s3.deleteObjects(multiObjectDeleteRequest);
            System.out.println("Multiple objects are deleted!");

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }



}
