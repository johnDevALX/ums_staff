package net.ekene.ums_staff.repository;

import net.ekene.user.UserData;
import net.ekene.user.StaffProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<StaffProfile, Long> {
    Optional<StaffProfile> findByUser(UserData userData);
}
