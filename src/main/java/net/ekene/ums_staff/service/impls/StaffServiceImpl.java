package net.ekene.ums_staff.service.impls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.ekene.user.UserData;
import net.ekene.user.StaffProfile;
import net.ekene.payload.StaffProfileDto;
import net.ekene.response.ClientResponseObj;
import net.ekene.ums_staff.feign.UserInterface;
import net.ekene.ums_staff.repository.StaffRepository;
import net.ekene.ums_staff.service.StaffService;
import net.ekene.utility.Address;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final UserInterface userInterface;

    @Override
    public ClientResponseObj createStaff(StaffProfileDto staffProfileDto) {
        log.info("staff profile dto  {}", staffProfileDto);

        UserData userByEmail = userInterface.getUserByEmail(staffProfileDto.getEmail());
        if (userByEmail.isUserVerified()){
            throw new RuntimeException();
        }

        log.info("Userdata --------  {}", userByEmail);
        StaffProfile staffProfile = StaffProfile.builder()
                .address(getAddress(staffProfileDto))
                .departmentName(staffProfileDto.getDepartmentName())
                .dob(staffProfileDto.getDob())
                .gender(staffProfileDto.getGender())
                .salary(staffProfileDto.getSalary())
                .user(userByEmail)
                .build();

        StaffProfile staffProfile1 = staffRepository.save(staffProfile);
        return staffProfile1.mapToClientResponseObj();
    }

    private Address getAddress(StaffProfileDto staffProfileDto) {
        return Address.builder()
                .street(staffProfileDto.getStreet())
                .city(staffProfileDto.getCity())
                .state(staffProfileDto.getState())
                .postalCode(staffProfileDto.getPostalCode())
                .country(staffProfileDto.getCountry())
                .build();
    }
}
