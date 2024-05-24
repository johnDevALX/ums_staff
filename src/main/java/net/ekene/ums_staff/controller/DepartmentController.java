package net.ekene.ums_staff.controller;

import lombok.RequiredArgsConstructor;
import net.ekene.payload.DepartmentDto;
import net.ekene.ums_staff.service.DepartmentService;
import net.ekene.utility.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/department/")
public class DepartmentController extends BaseController {
    private final DepartmentService departmentService;

    @PostMapping("create")
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDto departmentDto){
        return getResponse(HttpStatus.CREATED, "Created!", departmentService.createDepartment(departmentDto));
    }

    @PutMapping("update")
    public ResponseEntity<?> updateDepartment(@RequestParam String departmentName, @RequestBody DepartmentDto departmentDto){
        return getResponse(HttpStatus.OK, "Resource updated successfully!", departmentService.updateDepartment(departmentName, departmentDto));
    }

    @GetMapping("get-department")
    public ResponseEntity<?> getDepartment(@RequestParam String departmentName){
        return getResponse(HttpStatus.OK, "Resource retrieved successfully!", departmentService.getDepartment(departmentName));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> deleteDepartment(@RequestParam String departmentName){
        return getResponse(HttpStatus.OK, "Resource deleted successfully!", departmentService.deleteDepartment(departmentName));
    }
}
