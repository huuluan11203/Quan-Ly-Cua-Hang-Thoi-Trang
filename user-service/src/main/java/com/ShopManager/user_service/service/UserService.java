package com.ShopManager.user_service.service;

import com.ShopManager.user_service.DTO.request.UserCreationRequest;
import com.ShopManager.user_service.DTO.request.UserUpdateRequest;
import com.ShopManager.user_service.DTO.response.UserResponse;
import com.ShopManager.user_service.entity.Gender;
import com.ShopManager.user_service.entity.Position;
import com.ShopManager.user_service.entity.Status;
import com.ShopManager.user_service.entity.User;
import com.ShopManager.user_service.exception.AppException;
import com.ShopManager.user_service.exception.ErrorCode;
import com.ShopManager.user_service.mapper.UserMapper;
import com.ShopManager.user_service.repository.PositionRepository;
import com.ShopManager.user_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    PositionRepository positionRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {

        if (userRepository.existsByCIC(request.getCIC()))
            throw new AppException(ErrorCode.CIC_USED);

        User user = userMapper.toUser(request);
        // Position position = positionRepository.findById(PredefinedPosition.SALES_ASSOCIATE_POSITION).get();
        Position position = positionRepository.findById(request.getPosition())
                .orElseThrow(() -> new AppException(ErrorCode.POSITION_NOT_FOUND));

        Gender gender = Gender.toEnum(request.getGender());
        Status status = Status.toEnum(request.getStatus());

        user.setPosition(position);
        user.setStatus(status);
        user.setGender(gender);
        user = userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public UserResponse getMyInfo(String userId){
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()){
//            User user = userOptional.get();
//            return userMapper.toUserResponse(user);
//        }else {
//            throw new AppException(ErrorCode.USER_NOT_EXISTED);
//        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @PostAuthorize("returnObject.id == authentication.id")
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Position position = positionRepository.findById(request.getPosition())
                .orElseThrow(() -> new AppException(ErrorCode.POSITION_NOT_FOUND));

        Gender gender = Gender.toEnum(request.getGender());
        Status status = Status.toEnum(request.getStatus());

        userMapper.updateUser(user, request);
        user.setPosition(position);
        user.setStatus(status);
        user.setGender(gender);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

}
