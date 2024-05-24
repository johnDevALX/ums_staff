package net.ekene.ums_staff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan(basePackages = "net.ekene.user")
@EnableFeignClients
@EnableDiscoveryClient
public class UmsStaffApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmsStaffApplication.class, args);
    }

}