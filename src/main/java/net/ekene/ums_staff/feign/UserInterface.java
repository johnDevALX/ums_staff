package net.ekene.ums_staff.feign;

import net.ekene.user.UserData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("UMS-AUTH-SERVICE")
public interface UserInterface {

    @GetMapping("/auth/getUser")
    UserData getUserByEmail(@RequestParam String email);
}
