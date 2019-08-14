/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lingfeng.pets.entity.VaccineImageEntity;


/**
 * @author 谷春
 *
 */
@Mapper
public interface VaccineImageMapper {
    
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
    public Integer insertBatchVaccine(@Param("list")List<VaccineImageEntity> vaccineEntity);
    
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
     * @return List<VaccineImageEntity>
     * @throws Exception
     */
    public List<VaccineImageEntity> selectAllVaccine();
    
    /**
     * 根据发布id查询数据
     * @author 谷春
     * @param 
     * @return List<VaccineImageEntity>
     * @throws Exception
     */
    public List<VaccineImageEntity> selectVaccineByreleaseId(@Param("releaseId")String releaseId);
    
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
