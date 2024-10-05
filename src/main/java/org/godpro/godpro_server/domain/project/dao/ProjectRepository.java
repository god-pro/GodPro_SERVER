package org.godpro.godpro_server.domain.project.dao;

import org.godpro.godpro_server.domain.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
