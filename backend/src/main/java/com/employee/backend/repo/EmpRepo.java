package com.employee.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.employee.backend.entity.Emp;

public interface EmpRepo extends JpaRepository<Emp,Integer>{

    @Query(value = "SELECT emp_id,emp_contact,emp_name,Employee.address_id,dept_id FROM Employee INNER JOIN Address ON Employee.address_id = Address.address_id WHERE Address.city = ?1",nativeQuery = true)
    public List<Emp> getEmpByCity(String city);

    @Query(value = "SELECT emp_id,emp_contact,emp_name,address_id,Employee.dept_id FROM Employee INNER JOIN Department on Employee.dept_id = Department.dept_id WHERE dept_name = ?1",nativeQuery = true)
    public List<Emp> getEmpByDept(String deptName);
}
