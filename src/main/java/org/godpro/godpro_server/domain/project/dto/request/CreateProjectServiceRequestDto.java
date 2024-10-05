package org.godpro.godpro_server.domain.project.dto.request;

import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.domain.user.domain.User;

public record CreateProjectServiceRequestDto(
        String name,
        String shortDescription,
        String detailDescription,
        int back,
        int front,
        int pm,
        int design,
        int how_many,
        int how_long

) {
    public Project toEntity(User user) {
        return Project.builder()
                .name(name)
                .shortDescription(shortDescription)
                .detailDescription(detailDescription)
                .back(back)
                .front(front)
                .pm(pm)
                .design(design)
                .how_many(how_many)
                .how_long(how_long)
                .isRecruited(false)
                .build();
    }
}
