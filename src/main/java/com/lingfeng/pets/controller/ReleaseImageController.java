/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.ArrayList;
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
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.entity.ReleaseImageEntity;
import com.lingfeng.pets.service.ReleaseImageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */
@RestController("ReleaseImageController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/releaseImageController", description = "发布图片表的接口")
public class ReleaseImageController {
        /**
         * 注入发布图片表的实现类
         */
    @Autowired
    private ReleaseImageService releaseImageService;
    
    /**
     * 新增发布图片表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/releaseImage")
    @Timed
    @ApiOperation(value = "新增发布图片表信息",notes = "新增ReleaseImageEntity说明：参数必须全部都有！")
    public ServerResponse<Integer> insertReleaseImage(@ApiParam @Validated @RequestBody ReleaseImageEntity releaseImageEntity){
        releaseImageEntity.setId(UUID.randomUUID().toString());
        releaseImageEntity.setCreate_date(new Date());
        return ServerResponse.createBySuccess("新增发布信息成功！", releaseImageService.insertReleaseImage(releaseImageEntity));
    }
    
    /**
     * 批量新增
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/releaseImage/addBatch")
    @Timed
    @ApiOperation(value = "新增发布图片表信息",notes = "批量新增ReleaseImageEntity说明：要带参数，releaseId,image！")
    public ServerResponse<Integer> insertBatchReleaseImage(@ApiParam @Validated @RequestBody ReleaseImageEntity releaseImageEntity){
        if (!StringUtils.isNotBlank(releaseImageEntity.getReleaseId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布id不能为空！");
        }
        releaseImageEntity.setCreate_date(new Date());
        String[] releaseImage = releaseImageEntity.getImage().split(",");
        List<ReleaseImageEntity> releaseImageList = new ArrayList<ReleaseImageEntity>();
        for (String string : releaseImage) {
            releaseImageEntity.setReleaseId(string);
            releaseImageEntity.setId(UUID.randomUUID().toString());
            releaseImageList.add(releaseImageEntity);
        }
        return ServerResponse.createBySuccess("新增发布信息成功！", releaseImageService.insertBatchReleaseImage(releaseImageList));
    }
    /**
     * 删除发布图片表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/releaseImage")
    @Timed
    @ApiOperation(value = "删除发布图片表信息",notes = "删除ReleaseImageEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deleteReleaseImage(@ApiParam @Validated @RequestBody ReleaseImageEntity releaseImageEntity){
        if (!StringUtils.isNotBlank(releaseImageEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(releaseImageEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        releaseImageEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", releaseImageService.deleteReleaseImage(releaseImageEntity));
    }
    
    
    /**
     * 修改发布图片表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/releaseImage")
    @Timed
    @ApiOperation(value = "修改发布图片表信息",notes = "修改ReleaseImageEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updateReleaseImage(@ApiParam @Validated @RequestBody ReleaseImageEntity releaseImageEntity){
        if (!StringUtils.isNotBlank(releaseImageEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        releaseImageEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", releaseImageService.updateReleaseImage(releaseImageEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
   @GetMapping("/releaseImage/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<ReleaseImageEntity >> selectAllReleaseImage(){
        return ServerResponse.createBySuccess(releaseImageService.selectAllReleaseImage());
    }
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
   @GetMapping("/releaseImage/page")
    @Timed
    @ApiOperation(value = "模糊分页查询",notes = "模糊分页查询！")
    public ServerResponse<PageEntity<ReleaseImageEntity >> selectPageReleaseImage(@ApiParam @Validated @RequestBody ReleaseImageEntity releaseImageEntity){
       PageEntity pageEntity = new PageEntity();
       //封装总记录数
           int totalCount = releaseImageService.selectCount();
           pageEntity.setTotalCount(totalCount);
       if (releaseImageEntity.getCurrentPage() != null) {
          //封装当前页数
          pageEntity.setCurrPage(releaseImageEntity.getCurrentPage());
          //每页显示的数据
          int pageSize=5;
          pageEntity.setPageSize(pageSize);
          //封装总页数
          double tc = totalCount;
          Double num =Math.ceil(tc/pageSize);//向上取整
          pageEntity.setTotalPage(num.intValue());
          releaseImageEntity.setCurrIndex((releaseImageEntity.getCurrentPage() -1)*pageSize);
          releaseImageEntity.setPageSize(pageSize);
        //封装每页显示的数据
          List<ReleaseImageEntity> lists = releaseImageService.selectLikeReleaseImage(releaseImageEntity);
          pageEntity.setLists(lists);
      }else {
          List<ReleaseImageEntity> lists = releaseImageService.selectLikeReleaseImage(releaseImageEntity);
          pageEntity.setLists(lists);
      }
       return ServerResponse.createBySuccess(pageEntity);
    }
    
    /**
     * 根据发布id查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<List<ReleaseImageEntity>>
     * @throws Exception
     */
    @GetMapping("/releaseImage/{releaseId}")
    @Timed
    @ApiOperation(value = "根据发布id查询数据",notes = "接口说明：发布id不能为空！")
    public ServerResponse<List<ReleaseImageEntity>> selectReleaseImageByreleaseId(@PathVariable String releaseId){
        if (!StringUtils.isNotBlank(releaseId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布id不能为空！！");
        }
        return ServerResponse.createBySuccess(releaseImageService.selectReleaseImageByreleaseId(releaseId));
    }
    
}
