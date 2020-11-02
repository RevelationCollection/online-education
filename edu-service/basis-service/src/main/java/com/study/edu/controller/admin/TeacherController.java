package com.study.edu.controller.admin;

import com.study.edu.entity.Teacher;
import com.study.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api("讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    @ApiOperation("所有讲师列表")
    @GetMapping("list")
    public List<Teacher> list(){
        return teacherService.list(null);
    }
    @ApiOperation(value = "根据ID删除讲师", notes = "根据ID删除讲师")
    @DeleteMapping("remove/{id}")
    public boolean removeById(@ApiParam(value = "讲师ID", required = true) @PathVariable String id){
        return teacherService.removeById(id);
    }
}