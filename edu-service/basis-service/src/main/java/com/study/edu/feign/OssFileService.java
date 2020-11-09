package com.study.edu.feign;

import com.study.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient("service-oss")
public interface OssFileService {
    @GetMapping("/admin/oss/file/test")
    R test();

}
