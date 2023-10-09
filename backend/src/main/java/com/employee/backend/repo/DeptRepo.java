package com.employee.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.backend.entity.Dept;

public interface DeptRepo extends JpaRepository<Dept,Integer> {
    @Query(value = "SELECT * FROM department WHERE dept_name = ?1",nativeQuery = true)
    public Dept getDeptByName(String deptName);
}
