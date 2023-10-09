package com.employee.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpDTO {
    private int empId;
    private String empName;
    private String empContact;
    private AddressDTO empAddress;
    private DeptDTO empDept;
}
