/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.WithdrawEntity;
import com.lingfeng.pets.mapper.WithdrawMapper;
import com.lingfeng.pets.service.WithdrawService;

/**
 * @author 谷春
 *
 */
@Service
public class WithdrawServiceImpl implements WithdrawService{
    
    /**
     * 注入提现表的接口类
     */
    @Autowired
    private WithdrawMapper withdrawMapper;
    
    
    @Override
    public boolean addWithdraw(WithdrawEntity withdrawEntity) {
        return withdrawMapper.addWithdraw(withdrawEntity);
    }

    @Override
    public boolean deleteWithdraw(WithdrawEntity withdrawEntity) {
        return withdrawMapper.deleteWithdraw(withdrawEntity);
    }

    @Override
    public boolean updateWithdraw(WithdrawEntity withdrawEntity) {
        return withdrawMapper.updateWithdraw(withdrawEntity);
    }

    @Override
    public List<WithdrawEntity> selectWithdrawALL() {
        return withdrawMapper.selectWithdrawALL();
    }

}
