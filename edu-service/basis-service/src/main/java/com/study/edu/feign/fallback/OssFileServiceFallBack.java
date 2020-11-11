package com.study.edu.feign.fallback;

import com.study.edu.common.R;
import com.study.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OssFileServiceFallBack implements OssFileService {
    @Override
    public R test() {
        return R.error().data("msg","服务降级");
    }

    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error().data("msg","熔断保护");
    }
}
