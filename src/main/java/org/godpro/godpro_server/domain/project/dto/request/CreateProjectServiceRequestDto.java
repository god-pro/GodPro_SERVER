package org.godpro.godpro_server.domain.project.dto.request;

import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.domain.user.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CreateProjectServiceRequestDto(
        String name,
        String shortDescription,
        String detailDescription,
        int back,
        int front,
        int pm,
        int design,
        int eta,
        LocalDate recruitmentEndDate
) {
    public Project toEntity(User user) {
        LocalDateTime formattedRecruitmentEndDate = LocalDateTime.of(recruitmentEndDate, LocalTime.of(23, 59, 59));
        return Project.builder()
                .name(name)
                .shortDescription(shortDescription)
                .detailDescription(detailDescription)
                .back(back)
                .front(front)
                .pm(pm)
                .design(design)
                .eta(eta)
                .isRecruited(false)
                .recruitmentEndDate(formattedRecruitmentEndDate)
                .creator(user)
                .build();
    }
}
