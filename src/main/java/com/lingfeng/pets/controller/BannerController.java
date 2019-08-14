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
import com.lingfeng.pets.entity.BannerEntity;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.service.BannerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("BannerController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/bannerController", description = "首页轮播图的接口")
public class BannerController {
    
    /**
     * 注入banner表的实现类
     */
    @Autowired
    private BannerService bannerService;
    
    
    /**
     * 新增首页图片信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/banner")
    @Timed
    @ApiOperation(value = "单个新增首页图片信息",notes = "新增BannerEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertBanner(@ApiParam @Validated @RequestBody BannerEntity bannerEntity){
        bannerEntity.setId(UUID.randomUUID().toString());
        bannerEntity.setCreate_date(new Date());
        System.out.println("dgfdgdfgd");
        return ServerResponse.createBySuccess("新增发布信息成功！", bannerService.insertBanner(bannerEntity));
    }
    /**
     * 删除首页图片信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/banner")
    @Timed
    @ApiOperation(value = "单个删除首页图片信息",notes = "删除BannerEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteBanner(@ApiParam @Validated @RequestBody BannerEntity bannerEntity){
        if (!StringUtils.isNotBlank(bannerEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(bannerEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        bannerEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", bannerService.deleteBanner(bannerEntity));
    }
    
    
    /**
     * 删除首页图片信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/bannerbatch")
    @Timed
    @ApiOperation(value = "批量删除首页图片信息",notes = "删除BannerEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteBatchBanner(@ApiParam @Validated @RequestBody BannerEntity bannerEntity){
        if (!StringUtils.isNotBlank(bannerEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(bannerEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        String [] ids = bannerEntity.getId().split(",");
        Integer result = 0;
        for (String string : ids) {
            bannerEntity.setLastModel_date(new Date());
            bannerEntity.setId(string);
            bannerService.deleteBanner(bannerEntity);
            if (bannerService.deleteBanner(bannerEntity) >0 )result++;
        }
        return ServerResponse.createBySuccess("删除发布信息成功！", bannerService.deleteBanner(bannerEntity));
    }
    
    /**
     * 修改首页图片信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/banner")
    @Timed
    @ApiOperation(value = "修改首页图片信息",notes = "修改BannerEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateBanner(@ApiParam @Validated @RequestBody BannerEntity bannerEntity){
        if (!StringUtils.isNotBlank(bannerEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        bannerEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", bannerService.updateBanner(bannerEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @PostMapping("/banner/all")
    @Timed
    @ApiOperation(value = "页面查询数据",notes = "接口说明：查询所有未被删除的数据")
    public ServerResponse<List<BannerEntity >> selectBanner(){
        return ServerResponse.createBySuccess(bannerService.selectAllBanner());
    }
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/banner/likepage")
    @Timed
    @ApiOperation(value = "模糊分页查询",notes = "模糊分页查询！")
    public ServerResponse<PageEntity<BannerEntity >> Banner(@ApiParam @Validated @RequestBody BannerEntity bannerEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
            int totalCount = bannerService.selectCount();
            pageEntity.setTotalCount(totalCount);
        if (bannerEntity.getCurrentPage()!= null) {
           //封装当前页数
           pageEntity.setCurrPage(bannerEntity.getCurrentPage());
           //每页显示的数据
           int pageSize=5;
           pageEntity.setPageSize(pageSize);
           //封装总页数
           double tc = totalCount;
           Double num =Math.ceil(tc/pageSize);//向上取整
           pageEntity.setTotalPage(num.intValue());
           bannerEntity.setCurrIndex((bannerEntity.getCurrentPage() -1)*pageSize);
           bannerEntity.setPageSize(pageSize);
         //封装每页显示的数据
           List<BannerEntity> lists = bannerService.selectLikeBanner(bannerEntity);
           pageEntity.setLists(lists);
       }else {
           List<BannerEntity> lists = bannerService.selectLikeBanner(bannerEntity);
           pageEntity.setLists(lists);
       }
        return ServerResponse.createBySuccess(pageEntity);
    }
    
}
