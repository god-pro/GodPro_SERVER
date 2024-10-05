package org.godpro.godpro_server.domain.application.dto.request;

import org.godpro.godpro_server.domain.application.domain.Application;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.domain.user.domain.User;

public record ApplyRequestDto(
        String motivation,
        String contact,
        String part
) {
    public Application toEntity(Project project, User user) {
        return new Application(
                this.motivation,
                this.contact,
                this.part,
                user,
                project);
    }
}
