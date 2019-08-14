/**
 * 
 */
package com.lingfeng.pets.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingfeng.pets.config.ResponseCode;
import com.lingfeng.pets.config.ServerResponse;
import com.lingfeng.pets.entity.GrantEntity;
import com.lingfeng.pets.entity.PetcircleEntity;
import com.lingfeng.pets.service.GrantService;
import com.lingfeng.pets.service.PetcircleService;

import io.swagger.annotations.Api;

/**
 * @author 谷春
 *
 */
@RestController("GrantController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/grantController", description = "点赞表的接口")
public class GrantController {
    
    /**
     * 注入点赞表的接口
     */
    @Autowired
    private GrantService grantService;
    
    /**
     * 注入宠物圈的实现类
     */
    @Autowired
    private PetcircleService petcircleService;
    
    /**
     * 点赞的接口
     * @author 谷春
     * @param 
     * @return ServerResponse<String>
     * @throws Exception
     */
    @PostMapping("/grant/button")
    public ServerResponse<String> grantby(String releaseId,String uid){
        System.out.println("uid=="+uid);
        if (!StringUtils.isNotBlank(uid)||!StringUtils.isNotBlank(releaseId)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "参数不完整！");
        }
        GrantEntity grantEntity= grantService.selectGrantByuid(releaseId, uid);
        //根据发布查询数据
        PetcircleEntity petcircleEntity = new PetcircleEntity();
        petcircleEntity.setId(releaseId);
        List<PetcircleEntity> petlist= petcircleService.selectlikePetcircle(petcircleEntity);
        if (petlist == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "发布id有误！");
        }
        if (petlist.get(0).getPointRatio() == null ) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "数据有误！");
        }
        Integer numInteger = petlist.get(0).getPointRatio();
        if (grantEntity == null) {
            GrantEntity grantEntitys = new GrantEntity();
            grantEntitys.setId(UUID.randomUUID().toString());
            grantEntitys.setUid(uid);
            grantEntitys.setReleaseId(releaseId);
            grantEntitys.setCreate_date(new Date());
            grantService.insertGrant(grantEntitys);
            PetcircleEntity petcircleEntitys = new PetcircleEntity();
            Integer iiInteger = numInteger +1;
            petcircleEntitys.setPointRatio(iiInteger);
            petcircleEntitys.setId(releaseId);
            petcircleEntitys.setUpdate_date(new Date());
            petcircleService.updatePetcircle(petcircleEntitys);
        }else if (grantEntity.getIs_del() == 1) {
            //调用点赞表修改的方法,设置成为删除
            GrantEntity grantEntitys = new GrantEntity();
            grantEntitys.setIs_del(0);
            grantEntitys.setUid(uid);
            grantEntitys.setReleaseId(releaseId);
            grantEntitys.setUpdate_date(new Date());
            grantService.updateGrant(grantEntitys);
            PetcircleEntity petcircleEntitys = new PetcircleEntity();
            Integer iiInteger = numInteger +1;
            petcircleEntitys.setPointRatio(iiInteger);
            petcircleEntitys.setId(releaseId);
            petcircleEntitys.setUpdate_date(new Date());
            petcircleService.updatePetcircle(petcircleEntitys);
        }else if (grantEntity.getIs_del() == 0)  {
            //调用点赞表修改的方法,设置成为删除
            GrantEntity grantEntitys = new GrantEntity();
            grantEntitys.setIs_del(1);
            grantEntitys.setUid(uid);
            grantEntitys.setReleaseId(releaseId);
            grantEntitys.setUpdate_date(new Date());
            grantService.updateGrant(grantEntitys);
            PetcircleEntity petcircleEntitys = new PetcircleEntity();
            Integer iiInteger = numInteger - 1;
            petcircleEntitys.setPointRatio(iiInteger);
            petcircleEntitys.setId(releaseId);
            petcircleEntitys.setUpdate_date(new Date());
            petcircleService.updatePetcircle(petcircleEntitys);
        }
        return ServerResponse.createBySuccessMessage("成功！");
    }
    
}
