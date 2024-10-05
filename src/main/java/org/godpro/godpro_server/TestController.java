package org.godpro.godpro_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "health check";
    }
}
