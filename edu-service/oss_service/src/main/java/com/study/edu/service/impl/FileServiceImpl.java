package com.study.edu.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.study.edu.service.FileService;
import com.study.edu.util.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;

    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {
        String endpoint = ossProperties.getEndpoint();
        String bucketname = ossProperties.getBucketname();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        if (!ossClient.doesBucketExist(bucketname)) {
            //创建bucket
            ossClient.createBucket(bucketname);
            //设置oss实例的访问权限：公共读
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }
        //构建日期路径：avatar/2020/11/06/文件名
        String folder = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        //文件名：uuid.扩展名
        String fileName = UUID.randomUUID().toString();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String key = module + "/" + folder + "/" + fileName + fileExtension;
        //文件上传至阿里云
        ossClient.putObject(ossProperties.getBucketname(), key, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        //返回url地址
        //https://guli-file-191125.oss-cn-beijing.aliyuncs.com/avatar/default.jpg
        return "https://" + bucketname + "." + endpoint + "/" + key;
    }

    @Override
    public void removeFile(String url) {
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        String host = "https://" + bucketname + "." + endpoint + "/";
        String objectName = url.substring(host.length());
        // 删除文件。
        ossClient.deleteObject(bucketname, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
