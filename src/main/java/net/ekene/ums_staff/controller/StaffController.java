package net.ekene.ums_staff.controller;

import lombok.RequiredArgsConstructor;
import net.ekene.payload.StaffProfileDto;
import net.ekene.ums_staff.service.StaffService;
import net.ekene.utility.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/staff/")
public class StaffController extends BaseController {
    private final StaffService staffService;

    @PostMapping("create")
    public ResponseEntity<?> createStaff(@RequestBody StaffProfileDto staffProfileDto){
        return getResponse(HttpStatus.CREATED, "Created successfully", staffService.createStaff(staffProfileDto));
    }
}
