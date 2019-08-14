/**
 * 
 */
package com.lingfeng.pets.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingfeng.pets.entity.VaccineImageEntity;
import com.lingfeng.pets.mapper.VaccineImageMapper;
import com.lingfeng.pets.service.VaccineImageService;

/**
 * @author 谷春
 *
 */
@Service
public class VaccineImageServiceImpl implements VaccineImageService{
    
    /**
     * 注入疫苗证明表的接口类
     */
    @Autowired
    private VaccineImageMapper vaccineImageMapper;
    
    @Override
    public Integer insertVaccine(VaccineImageEntity vaccineEntity) {
        return vaccineImageMapper.insertVaccine(vaccineEntity);
    }

    @Override
    public Integer insertBatchVaccine(List<VaccineImageEntity> vaccineEntity) {
        return vaccineImageMapper.insertBatchVaccine(vaccineEntity);
    }

    @Override
    public Integer deleteVaccine(VaccineImageEntity vaccineEntity) {
        return vaccineImageMapper.deleteVaccine(vaccineEntity);
    }

    @Override
    public Integer updateVaccine(VaccineImageEntity vaccineEntity) {
        return vaccineImageMapper.updateVaccine(vaccineEntity);
    }

    @Override
    public List<VaccineImageEntity> selectAllVaccine() {
        return vaccineImageMapper.selectAllVaccine();
    }

    @Override
    public List<VaccineImageEntity> selectVaccineByreleaseId(String releaseId) {
        return vaccineImageMapper.selectVaccineByreleaseId(releaseId);
    }

    @Override
    public List<VaccineImageEntity> selectLikeVaccine(VaccineImageEntity vaccineEntity) {
        return vaccineImageMapper.selectLikeVaccine(vaccineEntity);
    }

    @Override
    public Integer selectCount() {
        return vaccineImageMapper.selectCount();
    }

}
