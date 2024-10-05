package org.godpro.godpro_server.domain.project.dto.request;

import org.godpro.godpro_server.domain.user.domain.User;

import java.time.LocalDate;

public record CreateProjectRequestDto(
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
    public CreateProjectServiceRequestDto toServiceRequest() {
        return new CreateProjectServiceRequestDto(
                name,
                shortDescription,
                detailDescription,
                back,
                front,
                pm,
                design,
                eta,
                recruitmentEndDate
        );
    }
}
