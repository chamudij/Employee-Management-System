package com.employee.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.ArrayList;

import com.employee.backend.repo.EmpRepo;
import com.employee.backend.entity.Emp;
import com.employee.backend.dto.DeptDTO;
import com.employee.backend.dto.EmpDTO;
import com.employee.backend.util.ResponseList;

@Service
@Transactional
public class EmpService{
    @Autowired
    private EmpRepo empRepo;

    @Autowired 
    private DeptService deptService;

    @Autowired
    private ModelMapper modelMapper;

    public String saveEmp(EmpDTO empDTO){
        if(empRepo.existsById(empDTO.getEmpId())){
            return ResponseList.RSP_DUPLICATED;
        }
        else{
            DeptDTO deptDTO = deptService.saveDept(empDTO.getEmpDept());
            empDTO.setEmpDept(deptDTO); 
            empRepo.save(modelMapper.map(empDTO,Emp.class));
            return ResponseList.RSP_SUCCESS;
        }
    }

    public String updateEmp(EmpDTO empDTO){
        if(empRepo.existsById(empDTO.getEmpId())){
            DeptDTO deptDTO = deptService.saveDept(empDTO.getEmpDept());
            empDTO.setEmpDept(deptDTO);
            empRepo.save(modelMapper.map(empDTO,Emp.class));
            return ResponseList.RSP_SUCCESS;
        }
        else{
            return ResponseList.RSP_NO_DATA_FOUND;
        }
    }

    public String deleteEmp(int empID){
        if(empRepo.existsById(empID)){
            empRepo.deleteById(empID);
            return ResponseList.RSP_SUCCESS;
        }
        else{
            return ResponseList.RSP_NO_DATA_FOUND;
        }
    }

    public EmpDTO searchEmp(int empID){
        if(empRepo.existsById(empID)){
            Emp employee = empRepo.findById(empID).orElse(null);
            return modelMapper.map(employee,EmpDTO.class);
        }
        else{
            return null;
        }
    }

    public List<EmpDTO> getAllEmp(){
        List<Emp> empList = empRepo.findAll();
        return modelMapper.map(empList,new TypeToken<ArrayList<EmpDTO>>(){}.getType());
    }

    public List<EmpDTO> getEmpByCity(String city){
        List<Emp> empList = empRepo.getEmpByCity(city);
        return modelMapper.map(empList,new TypeToken<ArrayList<EmpDTO>>(){}.getType());
    }

    public List<EmpDTO> getEmpByDept(String dept){
        List<Emp> empList = empRepo.getEmpByDept(dept);
        return modelMapper.map(empList,new TypeToken<ArrayList<EmpDTO>>(){}.getType());
    }

}
