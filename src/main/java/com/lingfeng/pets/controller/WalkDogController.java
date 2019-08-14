/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.entity.WalkDogEntity;
import com.lingfeng.pets.service.WalkDogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("WalkDogController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/walkDogController", description = "遛狗表的接口")
public class WalkDogController {
    
    /**
     * 注入遛狗表的是实现类
     */
    @Autowired
    private WalkDogService walkDogService;
    
    /**
     * 新增遛狗信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/walkDog")
    @Timed
    @ApiOperation(value = "新增遛狗信息",notes = "新增WalkDogEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertWalkDog(@ApiParam @Validated @RequestBody WalkDogEntity walkDogEntity){
        return ServerResponse.createBySuccess("新增发布信息成功！", walkDogService.insertWalkDog(walkDogEntity));
    }
    /**
     * 删除遛狗信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/walkDog")
    @Timed
    @ApiOperation(value = "删除遛狗信息",notes = "删除WalkDogEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteWalkDog(@ApiParam @Validated @RequestBody WalkDogEntity walkDogEntity){
        if (!StringUtils.isNotBlank(walkDogEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        walkDogEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", walkDogService.deleteWalkDog(walkDogEntity));
    }
    
    
    /**
     * 修改遛狗信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/walkDog")
    @Timed
    @ApiOperation(value = "修改遛狗信息",notes = "修改WalkDogEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateWalkDog(@ApiParam @Validated @RequestBody WalkDogEntity walkDogEntity){
        if (!StringUtils.isNotBlank(walkDogEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        walkDogEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改遛狗信息成功！", walkDogService.updateWalkDog(walkDogEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<WalkDogEntity>
     * @throws Exception
     */
   
    public ServerResponse<List<WalkDogEntity>> selectAllWalkDog(){
        return ServerResponse.createBySuccess(walkDogService.selectAllWalkDog());
    }
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return ServerResponse<PageEntity<WalkDogEntity>>
     * @throws Exception
     */
    @PostMapping("/walkDog/likePage")
    @Timed
    @ApiOperation(value = "模糊分页查询",notes = "模糊分页查询")
    public ServerResponse<PageEntity<WalkDogEntity>> selectLikeWalkDog(@ApiParam @Validated @RequestBody WalkDogEntity walkDogEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
            int totalCount = walkDogService.selectCount();
            pageEntity.setTotalCount(totalCount);
        if (walkDogEntity.getCurrentPage() != null) {
           //封装当前页数
           pageEntity.setCurrPage(walkDogEntity.getCurrentPage());
           //每页显示的数据
           int pageSize=5;
           pageEntity.setPageSize(pageSize);
           //封装总页数
           double tc = totalCount;
           Double num =Math.ceil(tc/pageSize);//向上取整
           pageEntity.setTotalPage(num.intValue());
           walkDogEntity.setCurrIndex((walkDogEntity.getCurrentPage() -1)*pageSize);
           walkDogEntity.setPageSize(pageSize);
         //封装每页显示的数据
           List<WalkDogEntity> lists = walkDogService.selectLikeWalkDog(walkDogEntity);
           pageEntity.setLists(lists);
       }else {
           List<WalkDogEntity> lists = walkDogService.selectLikeWalkDog(walkDogEntity);
           pageEntity.setLists(lists);
       }
        return ServerResponse.createBySuccess(pageEntity);
        
    }
}
