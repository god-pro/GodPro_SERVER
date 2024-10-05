package org.godpro.godpro_server.domain.user.dao;

import org.godpro.godpro_server.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
