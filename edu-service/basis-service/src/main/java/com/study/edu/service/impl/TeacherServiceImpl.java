package com.study.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.edu.common.R;
import com.study.edu.entity.Teacher;
import com.study.edu.entity.vo.TeacherQueryVo;
import com.study.edu.feign.OssFileService;
import com.study.edu.mapper.TeacherMapper;
import com.study.edu.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-11-02
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private OssFileService ossFileService;

    @Override
    public IPage<Teacher> selectPage(Long page, Long limit, TeacherQueryVo teacherQueryVo) {
        Page<Teacher> pageParam = new Page<>(page, limit);

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if (teacherQueryVo == null){
            return baseMapper.selectPage(pageParam, queryWrapper);
        }
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getJoinDateBegin();
        String end = teacherQueryVo.getJoinDateEnd();
        if (!StringUtils.isEmpty(name)) {
            //左%会使索引失效
            queryWrapper.likeRight("name", name);
        }
        if (level != null) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("join_date", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("join_date", end);
        }
        return baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectNameListByKey(String key) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.likeRight("name",key);
        return baseMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean removeAvatarById(String id) {
        Teacher teacher = baseMapper.selectById(id);
        if(teacher != null) {
            String avatar = teacher.getAvatar();
            if(!StringUtils.isEmpty(avatar)){
                //删除图片
                R r = ossFileService.removeFile(avatar);
                return r.getSuccess();
            }
        }
        return false;
    }
}
