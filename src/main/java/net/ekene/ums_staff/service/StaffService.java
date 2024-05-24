package net.ekene.ums_staff.service;

import net.ekene.payload.StaffProfileDto;
import net.ekene.response.ClientResponseObj;

public interface StaffService {
    ClientResponseObj createStaff(StaffProfileDto staffProfileDto);
}
