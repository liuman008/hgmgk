/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.BillEntity;
import com.lingfeng.pets.mapper.BillMapper;
import com.lingfeng.pets.service.BillService;


/**
 * @author 谷春
 *
 */
@Service
public class BillServiceImpl implements BillService{
    
    /*
     * 注入账单表的接口类
     */
    @Autowired
    private BillMapper billMapper;
    

    @Override
    public Integer insertBill(BillEntity billEntity) {
        return billMapper.insertBill(billEntity);
    }

    @Override
    public Integer deleteBill(BillEntity billEntity) {
        return billMapper.deleteBill(billEntity);
    }

    @Override
    public Integer updateBill(BillEntity billEntity) {
        return billMapper.updateBill(billEntity);
    }

    @Override
    public List<BillEntity> selectAllBill() {
        return billMapper.selectAllBill();
    }

    @Override
    public List<BillEntity> selectLikeBill(BillEntity billEntity) {
        return billMapper.selectLikeBill(billEntity);
    }

    @Override
    public List<BillEntity> selectBillByuserIdandRead(String userId) {
        return billMapper.selectBillByuserIdandRead(userId);
    }

}
