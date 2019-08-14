/**
 * 
 */
package com.lingfeng.pets.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lingfeng.pets.entity.PetcircleEntity;

/**
 * @author 谷春
 *
 */
@Mapper
public interface PetcircleMapper {
    /**
     * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertPetcircle(PetcircleEntity petcircleEntity);
    
    /**
     * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deletePetcircle(PetcircleEntity petcircleEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updatePetcircle(PetcircleEntity petcircleEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<PetcircleEntity>
     * @throws Exception
     */
    public List<PetcircleEntity> selectAllPetcircle();
    
    /**
     * 模糊查询
     * @author 谷春
     * @param 
     * @return List<PetcircleEntity>
     * @throws Exception
     */
    public List<PetcircleEntity> selectlikePetcircle(PetcircleEntity petcircleEntity);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer selectCount();
}
