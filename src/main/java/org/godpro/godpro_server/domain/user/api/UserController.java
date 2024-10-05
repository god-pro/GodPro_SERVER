package org.godpro.godpro_server.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.godpro.godpro_server.domain.user.application.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

}
