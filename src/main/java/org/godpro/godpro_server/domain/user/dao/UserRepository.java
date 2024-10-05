package org.godpro.godpro_server.domain.user.dao;

import org.godpro.godpro_server.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByKakaoId(String kakaoId);

    Boolean existsByKakaoId(String kakaoId);
}
