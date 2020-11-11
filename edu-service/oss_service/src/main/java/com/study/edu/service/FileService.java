package com.study.edu.service;

import java.io.InputStream;

public interface FileService {
    /**
     * https://help.aliyun.com/document_detail/84781.html?spm=a2c4g.11186623.6.940.4d8e6d39aAUf3w
     * 文件上传支阿里云
     * @param inputStream 文件流
     * @param module 数据格式
     * @param originalFilename 文件名称
     * @return 处理结果
     */
    String upload(InputStream inputStream, String module, String originalFilename);


    /**
     * 删除文件
     * @param url
     */
    void removeFile(String url);
}
