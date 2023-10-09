package com.employee.backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.backend.dto.DeptDTO;
import com.employee.backend.entity.Dept;
import com.employee.backend.repo.DeptRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeptService {
    @Autowired
    private DeptRepo deptRepo;

    @Autowired
    private ModelMapper modelMapper;

    public DeptDTO saveDept(DeptDTO deptDTO){
        Dept dept = deptRepo.getDeptByName(deptDTO.getDeptName());
        if(dept == null){
            deptRepo.save(modelMapper.map(deptDTO,Dept.class));
            dept = deptRepo.getDeptByName(deptDTO.getDeptName());
        }
        return modelMapper.map(dept,DeptDTO.class);
    }
}
