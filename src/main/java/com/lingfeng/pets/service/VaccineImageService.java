/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.VaccineImageEntity;

/**
 * @author 谷春
 *
 */

public interface VaccineImageService {
    
    /**
     * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertVaccine(VaccineImageEntity vaccineEntity);
    
    /**
     * 批量新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertBatchVaccine(List<VaccineImageEntity> vaccineEntity);
    
    /**
     * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteVaccine(VaccineImageEntity vaccineEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateVaccine(VaccineImageEntity vaccineEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<VaccineImageEntity> selectAllVaccine();
    
    /**
     * 根据发布id查询数据
     * @author 谷春
     * @param 
     * @return List<VaccineEntity>
     * @throws Exception
     */
    public List<VaccineImageEntity> selectVaccineByreleaseId(String releaseId);
    
    /**
     * 模糊分页查询
     * @author 谷春
     * @param 
     * @return List<VaccineImageEntity>
     * @throws Exception
     */
    public List<VaccineImageEntity> selectLikeVaccine(VaccineImageEntity vaccineEntity);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();

}
