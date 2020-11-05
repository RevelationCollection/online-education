package com.study.edu.test;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import org.junit.jupiter.api.Test;

/**
 * https://help.aliyun.com/document_detail/32008.html?spm=a2c4g.11186623.6.915.59d06328TFNSt7
 */
public class OssTest {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static String endpoint = "your endpoint";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private static String accessKeyId = "your accessKeyId";
    private static String accessKeySecret = "your accessKeySecret";
    private static String bucketName = "guli-file";

    private static OSS ossClient;
    static {
        // 创建OSSClient实例。
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    @Test
    public void testCreateBucket(){
        // 创建CreateBucketRequest对象。
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
        // 创建存储空间。
        ossClient.createBucket(createBucketRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testExist() {
        boolean exists = ossClient.doesBucketExist(bucketName);
        System.out.println(exists);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void testAccessControl() {
        // 设置存储空间的访问权限为：公共读。
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        ossClient.shutdown();
    }
}
