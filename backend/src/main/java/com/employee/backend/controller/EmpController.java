package com.employee.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.employee.backend.service.EmpService;
import com.employee.backend.util.ResponseList;
import com.employee.backend.dto.EmpDTO;
import com.employee.backend.dto.ResponseDTO;

@CrossOrigin
@RestController
@RequestMapping("api/employee")
public class EmpController {
    @Autowired
    private EmpService empService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/saveEmployee")
    public ResponseEntity<ResponseDTO> saveEmp(@RequestBody EmpDTO empDTO){
        try{
            String res = empService.saveEmp(empDTO);
            if (res.equals("00")){
                responseDTO.setCode(ResponseList.RSP_SUCCESS);
                responseDTO.setContent(empDTO);
                responseDTO.setMessage("Saved sucessfully");
                return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
            }
            else if (res.equals("06")){
                responseDTO.setCode(ResponseList.RSP_DUPLICATED);
                responseDTO.setContent(empDTO);
                responseDTO.setMessage("Employee already registerd");
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
            else{
                responseDTO.setCode(ResponseList.RSP_ERROR);
                responseDTO.setContent(null);
                responseDTO.setMessage("Error");
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex){
            responseDTO.setCode(ResponseList.RSP_ERROR);
            responseDTO.setContent(null);
            responseDTO.setMessage(ex.getMessage());
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<ResponseDTO> updateEmp(@RequestBody EmpDTO empDTO){
        try{
            String res = empService.updateEmp(empDTO);
            if (res.equals("00")){
                responseDTO.setCode(ResponseList.RSP_SUCCESS);
                responseDTO.setContent(empDTO);
                responseDTO.setMessage("Updated sucessfully");
                return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
            }
            else if (res.equals("01")){
                responseDTO.setCode(ResponseList.RSP_NO_DATA_FOUND);
                responseDTO.setContent(null);
                responseDTO.setMessage("Employee doesnot exist");
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
            else{
                responseDTO.setCode(ResponseList.RSP_ERROR);
                responseDTO.setContent(null);
                responseDTO.setMessage("Error");
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex){
            responseDTO.setCode(ResponseList.RSP_ERROR);
            responseDTO.setContent(null);
            responseDTO.setMessage(ex.getMessage());
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<ResponseDTO> getAllEmp(){
        try{
            List<EmpDTO> employeeList = empService.getAllEmp();
            if(employeeList.isEmpty()){
                responseDTO.setCode(ResponseList.RSP_NO_DATA_FOUND);
                responseDTO.setContent(employeeList);
                responseDTO.setMessage("No employees added");
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
            else{
                responseDTO.setCode(ResponseList.RSP_SUCCESS);
                responseDTO.setContent(employeeList);
                responseDTO.setMessage("Success");
                return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
            }
        }
        catch(Exception ex){
            responseDTO.setCode(ResponseList.RSP_ERROR);
            responseDTO.setContent(null);
            responseDTO.setMessage(ex.getMessage());
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/searchEmployee/{empID}")
     public ResponseEntity<ResponseDTO> searchEmpById(@PathVariable int empID){
        try{
            EmpDTO employee = empService.searchEmp(empID);

            if(employee != null){
                responseDTO.setCode(ResponseList.RSP_SUCCESS);
                responseDTO.setContent(employee);
                responseDTO.setMessage("Success");
                return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(ResponseList.RSP_NO_DATA_FOUND);
                responseDTO.setContent(null);
                responseDTO.setMessage("Employee doesn't exist for this empID");
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }  
        }
        catch(Exception ex){
            responseDTO.setCode(ResponseList.RSP_ERROR);
            responseDTO.setContent(null);
            responseDTO.setMessage(ex.getMessage());
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmpByCity/{city}")
    public ResponseEntity<ResponseDTO> getEmpByCity(@PathVariable String city){
        try{
            List<EmpDTO> employeeList= empService.getEmpByCity(city);

            if(employeeList.isEmpty()){
                responseDTO.setCode(ResponseList.RSP_NO_DATA_FOUND);
                responseDTO.setContent(employeeList);
                responseDTO.setMessage("No employees found with the address");
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
            else{
                responseDTO.setCode(ResponseList.RSP_SUCCESS);
                responseDTO.setContent(employeeList);
                responseDTO.setMessage("Success");
                return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
            } 
        }
        catch(Exception ex){
            responseDTO.setCode(ResponseList.RSP_ERROR);
            responseDTO.setContent(null);
            responseDTO.setMessage(ex.getMessage());
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmpByDept/{dept}")
    public ResponseEntity<ResponseDTO> getEmpByDept(@PathVariable String dept){
        try{
            List<EmpDTO> employeeList= empService.getEmpByDept(dept);

            if(employeeList.isEmpty()){
                responseDTO.setCode(ResponseList.RSP_NO_DATA_FOUND);
                responseDTO.setContent(employeeList);
                responseDTO.setMessage("No employees found with the address");
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
            else{
                responseDTO.setCode(ResponseList.RSP_SUCCESS);
                responseDTO.setContent(employeeList);
                responseDTO.setMessage("Success");
                return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
            } 
        }
        catch(Exception ex){
            responseDTO.setCode(ResponseList.RSP_ERROR);
            responseDTO.setContent(null);
            responseDTO.setMessage(ex.getMessage());
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteEmployee/{empID}")
    public ResponseEntity<ResponseDTO> deleteEmp(@PathVariable int empID){
       try{
            String res = empService.deleteEmp(empID);
            if (res.equals("00")){
                responseDTO.setCode(ResponseList.RSP_SUCCESS);
                responseDTO.setContent(null);
                responseDTO.setMessage("Deleted successfully");
                return new ResponseEntity<>(responseDTO,HttpStatus.ACCEPTED);
            }
            else{
                responseDTO.setCode(ResponseList.RSP_NO_DATA_FOUND);
                responseDTO.setContent(null);
                responseDTO.setMessage("Employee doesnot exist for this empID");
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception ex){
            responseDTO.setCode(ResponseList.RSP_ERROR);
            responseDTO.setContent(ex);
            responseDTO.setMessage(ex.getMessage());
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }
}
