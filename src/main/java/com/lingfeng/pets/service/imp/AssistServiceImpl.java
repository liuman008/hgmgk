/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.AssistEntity;
import com.lingfeng.pets.mapper.AssistMapper;
import com.lingfeng.pets.service.AssistService;

/**
 * @author 谷春
 *
 */
@Service
public class AssistServiceImpl implements AssistService{
    
    /**
     * 注入助养表的接口类
     */
    @Autowired
    private AssistMapper assistMapper;
    
    @Override
    public Integer insertAssist(AssistEntity assistEntity) {
        return assistMapper.insertAssist(assistEntity);
    }

    @Override
    public Integer deleteAssist(AssistEntity assistEntity) {
        return assistMapper.deleteAssist(assistEntity);
    }

    @Override
    public Integer updateAssist(AssistEntity assistEntity) {
        return assistMapper.updateAssist(assistEntity);
    }

    @Override
    public List<AssistEntity> selectAllAssist() {
        return assistMapper.selectAllAssist();
    }

    @Override
    public List<AssistEntity> selectAssistByreleaseId(String releaseId) {
        return assistMapper.selectAssistByreleaseId(releaseId);
    }

    @Override
    public List<AssistEntity> selectLikeAssist(AssistEntity assistEntity) {
        return assistMapper.selectLikeAssist(assistEntity);
    }

    @Override
    public Integer selectCount() {
        return assistMapper.selectCount();
    }

}
