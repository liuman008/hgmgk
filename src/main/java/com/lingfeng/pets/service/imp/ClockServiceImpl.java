/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.ClockEntity;
import com.lingfeng.pets.mapper.ClockMapper;
import com.lingfeng.pets.service.ClockService;

/**
 * @author 谷春
 *
 */
@Service
public class ClockServiceImpl implements ClockService{
    
    /**
     * 注入打卡表的接口类
     */
    @Autowired
    private ClockMapper clockMapper;
    
    @Override
    public Integer insertClock(ClockEntity clockEntity) {
        return clockMapper.insertClock(clockEntity);
    }

    @Override
    public Integer deleteClock(ClockEntity clockEntity) {
        return clockMapper.deleteClock(clockEntity);
    }

    @Override
    public Integer updateClock(ClockEntity clockEntity) {
        return clockMapper.updateClock(clockEntity);
    }

    @Override
    public List<ClockEntity> selectAllClock() {
        return clockMapper.selectAllClock();
    }

    @Override
    public List<ClockEntity> selectLikeClocks(ClockEntity clockEntity) {
        return clockMapper.selectLikeClocks(clockEntity);
    }

    @Override
    public Integer selectCount() {
        return clockMapper.selectCount();
    }

}
