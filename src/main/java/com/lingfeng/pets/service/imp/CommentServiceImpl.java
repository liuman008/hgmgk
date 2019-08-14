/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.CommentEntity;
import com.lingfeng.pets.mapper.CommentMapper;
import com.lingfeng.pets.service.CommentService;

/**
 * @author 谷春
 *
 */
@Service
public class CommentServiceImpl implements CommentService{
    
    /**
     * 注入评论表的接口类
     */
    @Autowired
    private CommentMapper commentMapper;
    
    @Override
    public Integer insertComment(CommentEntity commentEntity) {
        return commentMapper.insertComment(commentEntity);
    }

    @Override
    public Integer deleteComment(CommentEntity commentEntity) {
        return commentMapper.deleteComment(commentEntity);
    }

    @Override
    public Integer updateComment(CommentEntity commentEntity) {
        return commentMapper.updateComment(commentEntity);
    }

    @Override
    public List<CommentEntity> selectAllComment(String petcircleId) {
        return commentMapper.selectAllComment(petcircleId);
    }

    @Override
    public List<CommentEntity> selectAllCommentByparetId(String paretId) {
        return commentMapper.selectAllCommentByparetId(paretId);
    }

}
