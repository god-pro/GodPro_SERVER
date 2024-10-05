package org.godpro.godpro_server.domain.application.dao;

import org.godpro.godpro_server.domain.application.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
