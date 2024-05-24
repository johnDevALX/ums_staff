package net.ekene.ums_staff.service;

import net.ekene.user.Department;
import net.ekene.payload.DepartmentDto;
import net.ekene.response.DepartmentResponseObj;

public interface DepartmentService {
    DepartmentResponseObj createDepartment(DepartmentDto departmentDto);
    DepartmentResponseObj updateDepartment(String departmentName, DepartmentDto departmentDto);
    Department getDepartment(String departmentName);
    String deleteDepartment(String departmentName);
}
