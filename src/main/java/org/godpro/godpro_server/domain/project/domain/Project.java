package org.godpro.godpro_server.domain.project.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.godpro.godpro_server.domain.application.domain.Application;
import org.godpro.godpro_server.domain.user.domain.User;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name; // 프로젝트 이름

    @Column
    private String shortDescription; // 프로젝트 한 줄 소개

    @Column
    private String detailDescription; // 자세한 프로젝트 소개

    @Column
    private int back; // 모집할 백엔드 인원

    @Column
    private int front; // 모집할 프론트엔드 인원

    @Column
    private int pm; // 모집할 기획자 인원

    @Column
    private int design; // 모집할 디자이너 인원

    @Column
    private boolean isRecruited; // 모집 완료 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "project",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Application> applications;
}
