/**
 * 
 */
package com.lingfeng.pets.service;

import java.util.List;

import com.lingfeng.pets.entity.AddressEntity;

/**
 * @author 谷春
 *
 */
 
public interface AddressService {
    /**
     * 新增
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer insertAddress(AddressEntity addressEntity);
    
    /**
     * 删除
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer deleteAddress(AddressEntity addressEntity);
    
    /**
     * 修改
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public Integer updateAddress(AddressEntity addressEntity);
    
    /**
     * 查询所有
     * @author 谷春
     * @param 
     * @return List<ReleasePetEntity>
     * @throws Exception
     */
    public List<AddressEntity> selectAllAddress();
    
    /**
     * 模糊或分页查询
     * @author 谷春
     * @param 
     * @return List<AddressEntity>
     * @throws Exception
     */
    public List<AddressEntity> selectlikeAddress(AddressEntity addressEntity);
    
    /**
     * 查询总行数
     * @author 谷春
     * @param 
     * @return Integer
     * @throws Exception
     */
    public int selectCount();

}
