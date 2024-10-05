package org.godpro.godpro_server.domain.project.dto.request;

import org.godpro.godpro_server.domain.user.domain.User;

public record CreateProjectRequestDto(
        String name,
        String shortDescription,
        String detailDescription,
        int back,
        int front,
        int pm,
        int design,
        int how_many,
        int how_long,
        boolean isRecruited,
        User creator
) {
//    public CreateProjectServiceRequestDto toServiceRequest() {
//        return new CreateProjectServiceRequestDto(
//                name,
//                shortDescription,
//                detailDescription,
//                back,
//                front,
//                pm,
//                design,
//                how_many,
//                how_long,
//                isRecruited,
//                creator
//        );
//    }
}
