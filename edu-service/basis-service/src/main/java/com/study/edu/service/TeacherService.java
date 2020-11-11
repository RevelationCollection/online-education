package com.study.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.study.edu.entity.Teacher;
import com.study.edu.entity.vo.TeacherQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-11-02
 */
public interface TeacherService extends IService<Teacher> {

    IPage<Teacher> selectPage(Long page, Long limit, TeacherQueryVo teacherQueryVo);


    List<Map<String,Object>> selectNameListByKey(String key);


    boolean removeAvatarById(String id);
}
