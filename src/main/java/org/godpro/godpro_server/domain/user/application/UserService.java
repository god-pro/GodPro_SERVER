package org.godpro.godpro_server.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.user.dao.UserRepository;
import org.godpro.godpro_server.domain.user.domain.User;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.godpro.godpro_server.global.error.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    public ApiResponse<User> createUserInfo(CreateUserInfoServiceDto dto) {
//        User user = dto.toEntity();
//        User savedUser = userRepository.save(userInfo);
//        return ApiResponse.ok("사용자 정보를 성공적으로 등록하였습니다", savedUserInfo);
//    }

    public ApiResponse<List<User>> retrieveAllUser() {
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty()) {
            return ApiResponse.ok("UserInfo가 존재하지 않습니다.");
        }
        return ApiResponse.ok("UserInfo 목록을 성공적으로 조회했습니다.", userList);
    }

    public ApiResponse<User> retrieveOrCreateUser(Map<String, Object> user) {
        String uuid = user.get("id").toString();
        Optional<User> optionalUser = userRepository.findByKakaoId(uuid);
        if(optionalUser.isEmpty()) {
            System.out.println("11111111111111");
            return this.createUser(user);
        }
        User savedUser = optionalUser.get();
        return ApiResponse.ok("사용자 정보를 성공적으로 조회했습니다.", savedUser);
    }

    private ApiResponse<User> createUser(Map<String, Object> userInfo) {
        Map<String, Object> kakao_account = (Map<String, Object>) userInfo.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
        String uuid = userInfo.get("id").toString();
        String profile_image_url = (String) profile.get("profile_image_url");
        String name = (String) profile.get("nickname");
        System.out.println(name);
        User user = User.builder()
                .kakaoId(uuid)
                .name(name)
                .profileImageUrl(profile_image_url)
                .build();
        User savedUser = userRepository.save(user);
        return ApiResponse.ok("사용자 정보를 성공적으로 등록했습니다.", savedUser);
    }

    @Transactional(readOnly = true)
    public ApiResponse<User> retrieveUser(String kakaoId) {
        Optional<User> optionalUser = userRepository.findByKakaoId(kakaoId);
        if(optionalUser.isEmpty()) return ApiResponse.withError(ErrorCode.INVALID_USER_ID);
        User user = optionalUser.get();
        return ApiResponse.ok("사용자 정보를 성공적으로 조회했습니다.", user);
    }

    public Boolean isExisted(String kakaoId) {
        return userRepository.existsByKakaoId(kakaoId);
    }
}
