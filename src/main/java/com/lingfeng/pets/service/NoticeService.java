/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.NoticeEntity;

/**
 * @author 谷春
 *
 */
public interface NoticeService {
    
    /**
     * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertNotice(NoticeEntity noticeEntity);
    
    /**
     * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteNotice(NoticeEntity noticeEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateNotice(NoticeEntity noticeEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<NoticePetEntity>
     * @throws Exception
     */
    public List<NoticeEntity> selectAllNotice();
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return List<NoticePetEntity>
     * @throws Exception
     */
    public List<NoticeEntity> selectLikeNotice(NoticeEntity noticeEntity);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();

}
