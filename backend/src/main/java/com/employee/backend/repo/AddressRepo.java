package com.employee.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.backend.entity.Address;

public interface AddressRepo extends JpaRepository<Address,Integer> {
    
}
