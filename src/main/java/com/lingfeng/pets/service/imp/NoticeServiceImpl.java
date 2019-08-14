/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.NoticeEntity;
import com.lingfeng.pets.mapper.NoticeMapper;
import com.lingfeng.pets.service.NoticeService;

/**
 * @author 谷春
 *
 */
@Service
public class NoticeServiceImpl implements NoticeService{
    
    /**
     * 注入公告表的接口类
     */
    @Autowired
    private NoticeMapper noticeMapper;
    
    @Override
    public Integer insertNotice(NoticeEntity noticeEntity) {
        return noticeMapper.insertNotice(noticeEntity);
    }

    @Override
    public Integer deleteNotice(NoticeEntity noticeEntity) {
        return noticeMapper.deleteNotice(noticeEntity);
    }

    @Override
    public Integer updateNotice(NoticeEntity noticeEntity) {
        return noticeMapper.updateNotice(noticeEntity);
    }

    @Override
    public List<NoticeEntity> selectAllNotice() {
        return noticeMapper.selectAllNotice();
    }

    @Override
    public List<NoticeEntity> selectLikeNotice(NoticeEntity noticeEntity) {
        return noticeMapper.selectLikeNotice(noticeEntity);
    }

    @Override
    public Integer selectCount() {
        return noticeMapper.selectCount();
    }

}
