/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.AddressEntity;
import com.lingfeng.pets.mapper.AddressMapper;
import com.lingfeng.pets.service.AddressService;

/**
 * @author 谷春
 *
 */
@Service
public class AddressServiceImpl implements AddressService{
    
    /**
     * 注入地址表的接口类
     */
    @Autowired
    private AddressMapper addressMapper;
    
    @Override
    public Integer insertAddress(AddressEntity addressEntity) {
        return addressMapper.insertAddress(addressEntity);
    }

    @Override
    public Integer deleteAddress(AddressEntity addressEntity) {
        return addressMapper.deleteAddress(addressEntity);
    }

    @Override
    public Integer updateAddress(AddressEntity addressEntity) {
        return addressMapper.updateAddress(addressEntity);
    }

    @Override
    public List<AddressEntity> selectAllAddress() {
        return addressMapper.selectAllAddress();
    }

    @Override
    public List<AddressEntity> selectlikeAddress(AddressEntity addressEntity) {
        return addressMapper.selectlikeAddress(addressEntity);
    }

    @Override
    public int selectCount() {
        return addressMapper.selectCount();
    }

}
