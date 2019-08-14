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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.NoticeEntity;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("NoticeController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/noticeController", description = "养宠话你知的接口")
public class NoticeController {
    
    /**
     * 注入公告表的接口类
     */
    @Autowired
    private NoticeService noticeService;
    
    /**
     * 新增养宠话你知信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/Notice")
    @Timed
    @ApiOperation(value = "新增养宠话你知信息",notes = "新增NoticeEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertNotice(@ApiParam @Validated @RequestBody NoticeEntity noticeEntity){
        noticeEntity.setId(UUID.randomUUID().toString());
        noticeEntity.setCreate_date(new Date());
        return ServerResponse.createBySuccess("新增发布信息成功！", noticeService.insertNotice(noticeEntity));
    }
    /**
     * 删除养宠话你知信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/Notice")
    @Timed
    @ApiOperation(value = "删除养宠话你知信息",notes = "删除NoticeEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteNotice(@ApiParam @Validated @RequestBody NoticeEntity noticeEntity){
        if (!StringUtils.isNotBlank(noticeEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(noticeEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        noticeEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", noticeService.deleteNotice(noticeEntity));
    }
    
    
    /**
     * 修改养宠话你知信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/Notice")
    @Timed
    @ApiOperation(value = "修改养宠话你知信息",notes = "修改NoticeEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateNotice(@ApiParam @Validated @RequestBody NoticeEntity noticeEntity){
        if (!StringUtils.isNotBlank(noticeEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        noticeEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", noticeService.updateNotice(noticeEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<NoticePetEntity>
     * @throws Exception
     */
    @GetMapping("/Notice/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<NoticeEntity >> Notice(){
        return ServerResponse.createBySuccess(noticeService.selectAllNotice());
    }
    
    /**
     * 分页模糊查询
     * @author 谷春
     * @param 
     * @return ServerResponse<NoticePetEntity>
     * @throws Exception
     */
    @GetMapping("/Notice/likePage")
    @Timed
    @ApiOperation(value = "分页模糊查询",notes = "分页模糊查询！")
    public ServerResponse<PageEntity<NoticeEntity >> selectNotice(@ApiParam @Validated @RequestBody NoticeEntity noticeEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
            int totalCount = noticeService.selectCount();
            pageEntity.setTotalCount(totalCount);
        if (noticeEntity.getCurrentPage() != null) {
           //封装当前页数
           pageEntity.setCurrPage(noticeEntity.getCurrentPage());
           //每页显示的数据
           int pageSize=5;
           pageEntity.setPageSize(pageSize);
           //封装总页数
           double tc = totalCount;
           Double num =Math.ceil(tc/pageSize);//向上取整
           pageEntity.setTotalPage(num.intValue());
           noticeEntity.setCurrIndex((noticeEntity.getCurrentPage() -1)*pageSize);
           noticeEntity.setPageSize(pageSize);
         //封装每页显示的数据
           List<NoticeEntity> lists = noticeService.selectLikeNotice(noticeEntity);
           pageEntity.setLists(lists);
       }else {
           List<NoticeEntity> lists = noticeService.selectLikeNotice(noticeEntity);
           pageEntity.setLists(lists);
       }
        return ServerResponse.createBySuccess(pageEntity);
        
    }
    

}
