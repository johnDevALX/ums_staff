package net.ekene.ums_staff.service.impls;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.ekene.user.UserData;
import net.ekene.user.Department;
import net.ekene.user.StaffProfile;
import net.ekene.payload.DepartmentDto;
import net.ekene.response.DepartmentResponseObj;
import net.ekene.ums_staff.feign.UserInterface;
import net.ekene.ums_staff.repository.DepartmentRepository;
import net.ekene.ums_staff.repository.StaffRepository;
import net.ekene.ums_staff.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final StaffRepository staffRepository;
    private final UserInterface userInterface;


    @Override
    public DepartmentResponseObj createDepartment(DepartmentDto departmentDto) {
        UserData userByEmail = userInterface.getUserByEmail(departmentDto.getHeadOfDepartmentEmail());
        StaffProfile staffProfile = staffRepository.findByUser(userByEmail).orElseThrow(() -> new EntityNotFoundException("Staff not found for User with email: " + departmentDto.getHeadOfDepartmentEmail()));
        Department department = Department.builder()
                .departmentName(departmentDto.getDepartmentName())
                .headOfDepartment(staffProfile)
                .dateCreated(departmentDto.getDateCreated())
                .accredited(departmentDto.isAccredited())
                .build();
        Department department1 = departmentRepository.save(department);
        return department1.returnResponse();
    }

    @Override
    public DepartmentResponseObj updateDepartment(String departmentName, DepartmentDto departmentDto) {
        Department department = departmentRepository.findByDepartmentNameIgnoreCase(departmentName).orElseThrow(() -> new RuntimeException("Department not found!"));
        if (Objects.nonNull(department.getDepartmentName())){
            department.setDepartmentName(departmentDto.getDepartmentName());
        }
        if (Objects.nonNull(departmentDto.getHeadOfDepartmentEmail())){
            UserData userByEmail = userInterface.getUserByEmail(departmentDto.getHeadOfDepartmentEmail());
            StaffProfile staffProfile = staffRepository.findByUser(userByEmail).orElseThrow(() -> new EntityNotFoundException("Staff not found for User with email: " + departmentDto.getHeadOfDepartmentEmail()));
            department.setHeadOfDepartment(staffProfile);
        }
        if (Objects.nonNull(departmentDto.getDateCreated())){
            department.setDateCreated(departmentDto.getDateCreated());
        }
        if (Objects.nonNull(departmentDto.isAccredited())){
            department.setAccredited(departmentDto.isAccredited());
        }
        Department department1 = departmentRepository.save(department);
        return department1.returnResponse();
    }

    @Override
    public Department getDepartment(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName).orElseThrow(() -> new EntityNotFoundException("Department does not exist..." + departmentName));
    }

    @Override
    public String deleteDepartment(String departmentName) {
        if (Objects.nonNull(departmentName)) {
            departmentRepository.deleteByDepartmentNameIgnoreCase(departmentName);
            return "Department deleted!";
        } else {
            throw new RuntimeException("Department does not exist");
        }
    }
}
