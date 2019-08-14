/**
 * 
 */
package com.lingfeng.pets.controller;

import java.text.SimpleDateFormat;
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
import com.lingfeng.pets.entity.ClockEntity;
import com.lingfeng.pets.entity.PageEntity;
import com.lingfeng.pets.entity.ReleaseImageEntity;
import com.lingfeng.pets.service.ClockService;
import com.lingfeng.pets.service.ReleaseImageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 谷春
 *
 */

@RestController("ClockController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/clockController", description = "打卡的接口")
public class ClockController {
    
    /**
     * 注入打卡表的实现类
     */
    @Autowired
    private ClockService clockService;
    
    
    /**
     * 注入发布图片表的实现类
     */
    @Autowired
    private ReleaseImageService releaseImageService;
    
    /**
     * 新增打卡信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PostMapping("/clock")
    public ServerResponse<Integer> insertClock(@ApiParam @Validated @RequestBody ClockEntity clockEntity){
        if (!StringUtils.isNotBlank(clockEntity.getUserId())||!StringUtils.isNotBlank(clockEntity.getAddress())||!StringUtils.isNotBlank(clockEntity.getContent())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布信息不全！！！");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String nowStr=formatter.format(new Date());
        ClockEntity clockEntitys = new ClockEntity();
        clockEntitys.setCreate_date(nowStr);
        clockEntitys.setUserId(clockEntity.getUserId());
        clockEntitys.setReleaseId(clockEntity.getReleaseId());
        List<ClockEntity> rowInteger = clockService.selectLikeClocks(clockEntitys);
        if (rowInteger.size()>0) {
            return ServerResponse.createByErrorMessage("今日已打过卡！");
        }else {
            String cid = UUID.randomUUID().toString();
            clockEntity.setId(cid);
            clockEntity.setCreate_date(nowStr);
            String [] images = clockEntity.getImage().split(",");
            List<ReleaseImageEntity> iList = new ArrayList<ReleaseImageEntity>();
            for (String string : images) {
                ReleaseImageEntity releaseImageEntity = new ReleaseImageEntity();
                releaseImageEntity.setId(UUID.randomUUID().toString());
                releaseImageEntity.setCreate_date(new Date());
                releaseImageEntity.setImage(string);
                releaseImageEntity.setReleaseId(cid);
                iList.add(releaseImageEntity);
            }
            releaseImageService.insertBatchReleaseImage(iList);
            return ServerResponse.createBySuccess("打卡成功！", clockService.insertClock(clockEntity));
        }
        }
    
    /**
     * 删除宠物圈表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @DeleteMapping("/clock")
    @Timed
    @ApiOperation(value = "删除宠物圈表信息",notes = "删除PetcircleEntity说明：根据id删除数据,还有最后操作人id！")
    public ServerResponse<Integer> deletePetcircle(@ApiParam @Validated @RequestBody ClockEntity clockEntity){
        if (!StringUtils.isNotBlank(clockEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }else if (!StringUtils.isNotBlank(clockEntity.getLastModel_by())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "最后操作人不能为空！");
        }
        clockEntity.setLastModel_date(new Date());
        return ServerResponse.createBySuccess("删除发布信息成功！", clockService.deleteClock(clockEntity));
    }
    
    
    /**
     * 修改打卡表信息
     * @author 谷春
     * @param 
     * @return ServerResponse<Integer>
     * @throws Exception
     */
    @PutMapping("/clock")
    @Timed
    @ApiOperation(value = "修改宠物圈表信息",notes = "修改PetcircleEntity说明：根据id修改数据,id不能为空！")
    public ServerResponse<Integer> updatePetcircle(@ApiParam @Validated @RequestBody ClockEntity clockEntity){
        if (!StringUtils.isNotBlank(clockEntity.getId())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "id不能为空！！");
        }
        clockEntity.setUpdate_date(new Date());
        return ServerResponse.createBySuccess("修改发布信息成功！", clockService.updateClock(clockEntity));
    }
    
    /**
     * 查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<ReleasePetEntity>
     * @throws Exception
     */
    @GetMapping("/clock/all")
    @Timed
    @ApiOperation(value = "查看所有",notes = "查看所有！")
    public ServerResponse<List<ClockEntity >> Petcircle(){
        return ServerResponse.createBySuccess(clockService.selectAllClock());
    }
    
    
    /**
     *模糊查询
     * @author 谷春
     * @param 
     * @return ServerResponse<List<PetcircleEntity>>
     * @throws Exception
     */
    @PostMapping("/clock/like")
    @Timed
    @ApiOperation(value = "模糊查询",notes = "模糊查询！")
    public ServerResponse<PageEntity<ClockEntity>> seleectlikePetcircle(@ApiParam @Validated @RequestBody ClockEntity clockEntity){
        PageEntity pageEntity = new PageEntity();
        //封装总记录数
            int totalCount = clockService.selectCount();
            pageEntity.setTotalCount(totalCount);
        if (clockEntity.getCurrentPage() != null) {
           //封装当前页数
           pageEntity.setCurrPage(clockEntity.getCurrentPage());
           //每页显示的数据
           int pageSize=5;
           pageEntity.setPageSize(pageSize);
           //封装总页数
           double tc = totalCount;
           Double num =Math.ceil(tc/pageSize);//向上取整
           pageEntity.setTotalPage(num.intValue());
           clockEntity.setCurrIndex((clockEntity.getCurrentPage() -1)*pageSize);
           clockEntity.setPageSize(pageSize);
         //封装每页显示的数据
           List<ClockEntity> lists = clockService.selectLikeClocks(clockEntity);
           pageEntity.setLists(lists);
       }else {
           List<ClockEntity> lists = clockService.selectLikeClocks(clockEntity);
           pageEntity.setLists(lists);
       }
        return ServerResponse.createBySuccess(pageEntity);
    }

    
    /**
     * 根据用户id和发布id查询数据
     * @author 谷春
     * @param 
     * @return ServerResponse<List<PetcircleEntity>>
     * @throws Exception
     */
    @GetMapping("/clock/{userId}/{releaseId}")
    @Timed
    @ApiOperation(value = "根据用户id和发布id查询数据",notes = "接口说明：只要传用户id和发布id")
    public ServerResponse<List<ClockEntity>> selectByuserIdPetcircle(@PathVariable String userId,@PathVariable String releaseId){
        if (!StringUtils.isNotBlank(userId)||!StringUtils.isNotBlank(releaseId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "参数不全！！");
        }
     
        ClockEntity clockEntity = new ClockEntity();
        clockEntity.setUserId(userId);
        clockEntity.setReleaseId(releaseId);
        List<ClockEntity> clockEntities = clockService.selectLikeClocks(clockEntity);
        for (ClockEntity clockEntity2 : clockEntities) {
            //根据发布id查询数据
            List<ReleaseImageEntity> reList = releaseImageService.selectReleaseImageByreleaseId(clockEntity2.getId());
            if (reList.isEmpty()) return ServerResponse.createByErrorMessage("没查询到数据！");
            String imageString = null;
            for (int i = 0; i < reList.size(); i++) {
                imageString = reList.get(i).getImage()+imageString;
            }
            clockEntity2.setImage(imageString);
        }
        return ServerResponse.createBySuccess(clockEntities);
    }
}
