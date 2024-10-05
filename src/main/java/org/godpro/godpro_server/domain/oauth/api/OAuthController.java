package org.godpro.godpro_server.domain.oauth.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.oauth.application.KakaoService;
import org.godpro.godpro_server.domain.user.application.UserService;
import org.godpro.godpro_server.global.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {
    private final KakaoService kakaoService;
    private final UserService userService;

    @Operation(summary="리다이렉션 url 반환 API")
    @GetMapping("/login")
    public ApiResponse<String> getKakaoLoginUrl() {
        return kakaoService.getKakaoLoginUrl();
    }

    @Operation(summary="로그인 후 콜백 메소드")
    @GetMapping("/callback")
    public ApiResponse<String> kakaoLogin(@RequestParam("code") String code) {
        System.out.println("0000000000000000000000000");
        String accessToken = kakaoService.getAccessToken(code);
        Map<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
        return ApiResponse.ok("사용자의 user ID를 성공적으로 조회했습니다.", userService.retrieveOrCreateUser(userInfo).getData().getKakaoId());
    }
}
