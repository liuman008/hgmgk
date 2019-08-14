/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lingfeng.pets.entity.CommentEntity;

/**
 * @author 谷春
 *
 */
@Mapper
public interface CommentMapper {
    /**
     * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertComment(CommentEntity commentEntity);
    
    /**
     * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteComment(CommentEntity commentEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateComment(CommentEntity commentEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<CommentEntity> selectAllComment(@Param("petcircleId")String petcircleId);
    
    /**
     * 根据父级id查询数据
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<CommentEntity> selectAllCommentByparetId(@Param("paretId")String paretId);
}
