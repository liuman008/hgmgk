
/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.CommentEntity;
import com.lingfeng.pets.entity.PetcircleEntity;
import com.lingfeng.pets.entity.UserEntity;
import com.lingfeng.pets.service.CommentService;
import com.lingfeng.pets.service.PetcircleService;
import com.lingfeng.pets.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("CommentController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/commentController", description = "评论表的接口")
public class CommentController {
    
    /**
     * 注入评论表的实现类
     */
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private PetcircleService petcircleService;
    
    /**
             * 注入用户表的实现类
     */
    @Autowired
    private UserService userService;
    
    /**
     * 新增评论表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/comment")
    @Timed
    @ApiOperation(value = "新增评论表信息",notes = "新增CommentEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertComment(@ApiParam @Validated @RequestBody CommentEntity commentEntity){
        
        
        if (!StringUtils.isNotBlank(commentEntity.getParetId())) {
            commentEntity.setParetId("0");
        }else if (!StringUtils.isNotBlank(commentEntity.getCommentUserId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "用户id为空！！");
        }
         //判断用户是否注册
        UserEntity userEntity = userService.selectUserByid(commentEntity.getCommentUserId());
        if (userEntity == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_REGISTER.getCode(), "用户表没查询到数据，请检查userId！");
        }
        if (!StringUtils.isNotBlank(userEntity.getPhone())||!StringUtils.isNotBlank(userEntity.getUserName())) {
            return ServerResponse.createByErrorMessage("需要注册才能评论！！");
        }
        
        commentEntity.setId(UUID.randomUUID().toString());
        commentEntity.setCreate_date(new Date());
        //根据发布查询数据
        PetcircleEntity petcircleEntity = new PetcircleEntity();
        petcircleEntity.setId(commentEntity.getPetcircleId());
        List<PetcircleEntity> petlist= petcircleService.selectlikePetcircle(petcircleEntity);
        Integer numInteger = petlist.get(0).getCommentNum();
        PetcircleEntity petcircleEntitys = new PetcircleEntity();
        petcircleEntitys.setCommentNum(numInteger+1);
        petcircleEntitys.setId(commentEntity.getPetcircleId());
        petcircleService.updatePetcircle(petcircleEntitys);
        return ServerResponse.createBySuccess("新增发布信息成功！", commentService.insertComment(commentEntity));
    }
    /**
     * 删除评论表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     * 
     */
    @DeleteMapping("/comment")
    @Timed
    @ApiOperation(value = "删除评论表信息",notes = "删除CommentEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteComment(@ApiParam @Validated @RequestBody CommentEntity commentEntity){
        if (!StringUtils.isNotBlank(commentEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(commentEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        commentEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", commentService.deleteComment(commentEntity));
    }
    
    
    /**
     * 修改评论表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws ption
     */
    @PutMapping("/comment")
    @Timed
    @ApiOperation(value = "修改评论表信息",notes = "修改CommentEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateComment(@ApiParam @Validated @RequestBody CommentEntity commentEntity){
        if (!StringUtils.isNotBlank(commentEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        commentEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", commentService.updateComment(commentEntity));
    }
    
    /**
     * 查询第一级评论
     * 
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/comment/one/{petcircleId}")
    @Timed
    @ApiOperation(value = "查询第一级评论",notes = "接口说明：petcircleId为发布id")
    public ServerResponse<List<CommentEntity >> selectAllComment(@PathVariable String petcircleId){
        if (!StringUtils.isNotBlank(petcircleId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "petcircleId不能为空！！");
        }
        List<CommentEntity> commentList = commentService.selectAllComment(petcircleId);
        for (CommentEntity commentEntity : commentList) {
            //根据用户名查询数据
           UserEntity userEntity =  userService.selectUserByid(commentEntity.getCommentUserId());
           commentEntity.setUserLogo(userEntity.getImage());
           commentEntity.setUserName(userEntity.getUserName());
        }
        return ServerResponse.createBySuccess(commentList);
    }

    /**
     * 根据父级查询出数据
     * @author 谷春
     * @param 
     * @return ServerResponse<List<CommentEntity>>
     * @throws Exception
     */
    @GetMapping("/comment/paretId/{paretId}")
    @Timed
    @ApiOperation(value = "根据父级查询出数据",notes = "接口说明：paretId为父级id")
    public ServerResponse<List<CommentEntity >> selectAllCommentByparetId(@PathVariable String paretId){
        if (!StringUtils.isNotBlank(paretId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "父级id不能为空！！");
        }
        List<CommentEntity> commentList = commentService.selectAllCommentByparetId(paretId);
        for (CommentEntity commentEntity : commentList) {
            //根据用户名查询数据
           UserEntity userEntity =  userService.selectUserByid(commentEntity.getCommentUserId());
           commentEntity.setUserLogo(userEntity.getImage());
           commentEntity.setUserName(userEntity.getUserName());
        }
        return ServerResponse.createBySuccess(commentList);
    }
}
