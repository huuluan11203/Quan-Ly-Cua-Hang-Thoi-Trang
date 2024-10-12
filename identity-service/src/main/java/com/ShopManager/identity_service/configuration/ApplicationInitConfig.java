package com.ShopManager.identity_service.configuration;

import java.time.LocalDate;
import java.util.HashSet;

import com.ShopManager.identity_service.DTO.request.UserCreationRequest;
import com.ShopManager.identity_service.client.UserClient;
import com.ShopManager.identity_service.entity.Account;
import com.ShopManager.identity_service.exception.AppException;
import com.ShopManager.identity_service.exception.ErrorCode;
import com.ShopManager.identity_service.repository.PermissionRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ShopManager.identity_service.constant.PredefinedRole;
import com.ShopManager.identity_service.entity.Role;
import com.ShopManager.identity_service.repository.RoleRepository;
import com.ShopManager.identity_service.repository.AccountRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    UserClient userClient;


    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @NonFinal
    static final String EMPLOYEE_USER_NAME = "employee";

    @NonFinal
    static final String EMPLOYEE_PASSWORD = "employee";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(AccountRepository accountRepository, RoleRepository roleRepository) {
        return args -> {
            if (accountRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {

                Role adminRole = roleRepository.findById(PredefinedRole.ADMIN_ROLE).get();

                var roles = new HashSet<Role>();
                roles.add(adminRole);

                Account account = Account.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .build();
                accountRepository.save(account);

                UserCreationRequest userCreationRequest = UserCreationRequest.builder()
                        .id(account.getId())
                        .lastName("Bùi Hữu")
                        .firstName("Luân")
                        .CIC("083203011547")
                        .dob(LocalDate.parse("2003-10-14"))
                        .phoneNumber("0878678691")
                        .gender("Male")
                        .position("Manager")
                        .status("Active")
                        .startDate(LocalDate.parse("2023-01-01"))
                        .build();
                userClient.createUser(userCreationRequest);


            }

            if (accountRepository.findByUsername(EMPLOYEE_USER_NAME).isEmpty()){
                Role employeeRole = roleRepository.findById(PredefinedRole.EMPLOYEE_ROLE)
                        .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));

                var roles = new HashSet<Role>();
                roles.add(employeeRole);

                Account account = Account.builder()
                        .username(EMPLOYEE_USER_NAME)
                        .password(passwordEncoder.encode(EMPLOYEE_PASSWORD))
                        .roles(roles)
                        .build();
                accountRepository.save(account);

                UserCreationRequest userCreationRequest = UserCreationRequest.builder()
                        .id(account.getId())
                        .lastName("Nguyễn Thị")
                        .firstName("Xuân Mai")
                        .CIC("092304002482")
                        .dob(LocalDate.parse("2004-04-23"))
                        .phoneNumber("0363645182")
                        .gender("Female")
                        .position("Sales_Associate")
                        .status("Active")
                        .startDate(LocalDate.parse("2023-01-01"))
                        .build();
                userClient.createUser(userCreationRequest);

            }
//            if (permissionRepository.findAll().isEmpty()){
//                String[][] permissions = {
//                        {"admin_full_access", "Toàn quyền truy cập vào hệ thống."},
//                        {"user_view_profile", "Xem thông tin hồ sơ cá nhân."},
//                        {"user_edit_profile", "Chỉnh sửa thông tin hồ sơ cá nhân."},
//                        {"user_view_product", "Xem danh sách sản phẩm."},
//                        {"user_create_order", "Đặt hàng mới."},
//                        {"user_view_order", "Xem thông tin đơn hàng."},
//                        {"user_rate_product", "Đánh giá sản phẩm."},
//                        {"user_view_cart", "Xem giỏ hàng."},
//                        {"user_add_to_cart", "Thêm sản phẩm vào giỏ hàng."},
//                        {"user_remove_from_cart", "Xóa sản phẩm khỏi giỏ hàng."},
//                        {"user_checkout", "Thanh toán và hoàn tất đơn hàng."},
//                        {"user_view_order_history", "Xem lịch sử đơn hàng."},
//                        {"user_request_return", "Yêu cầu trả lại sản phẩm."},
//                        {"employee_view_order", "Xem danh sách đơn hàng."},
//                        {"employee_process_order", "Xử lý và cập nhật trạng thái đơn hàng."},
//                        {"employee_view_product", "Xem thông tin sản phẩm."},
//                        {"employee_manage_inventory", "Quản lý tồn kho sản phẩm."},
//                };
//
//                Permission newPermission = new Permission();
//                for (String[] permission : permissions) {
//                    newPermission.setName(permission[0]);
//                    newPermission.setDescription(permission[1]);
//                    permissionRepository.save(newPermission);
//                }

//                List<Permission> employeePermissions =
//                        permissionRepository.findByNameStartingWith("employee_");
//                List<Permission> userPermissions =
//                        permissionRepository.findByNameStartingWith("user_");
//                List<Permission> adminPermissions =
//                        permissionRepository.findByNameStartingWith("admin_");
//
//                Role employeeRole = roleRepository.findByName("EMPLOYEE");
//                Role userRole = roleRepository.findByName("USER");
//                Role adminRole = roleRepository.findByName("ADMIN");
//
//                employeeRole.getPermissions().addAll(employeePermissions);
//                userRole.getPermissions().addAll(userPermissions);
//                adminRole.getPermissions().addAll(adminPermissions);
//
//                roleRepository.save(employeeRole);
//                roleRepository.save(userRole);
//                roleRepository.save(adminRole);
//            }
//
//
        };
    }
}
