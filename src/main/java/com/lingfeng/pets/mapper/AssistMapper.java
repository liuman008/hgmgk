/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lingfeng.pets.entity.AssistEntity;

/**
 * @author 谷春
 *
 */
@Mapper
public interface AssistMapper {
    
    /**
             * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertAssist(AssistEntity assistEntity);
    
    /**
             * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteAssist(AssistEntity assistEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateAssist(AssistEntity assistEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<AssistEntity> selectAllAssist();

    /**
     * 
     * @author 谷春
     * @param 
     * @return List<AssistEntity>
     * @throws Exception
     */
    public List<AssistEntity> selectAssistByreleaseId(@Param("releaseId") String releaseId);
    
    /**
     * 模糊查询
     * @author 谷春
     * @param 
     * @return List<AssistEntity>
     * @throws Exception
     */
    public List<AssistEntity> selectLikeAssist(AssistEntity assistEntity);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();
}
