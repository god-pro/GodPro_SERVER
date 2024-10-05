package org.godpro.godpro_server.domain.project.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.godpro.godpro_server.domain.application.domain.Application;
import org.godpro.godpro_server.domain.user.domain.User;

import java.time.LocalDateTime;
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
    private String name;

    @Column
    private String shortDescription; //한줄 소개

    @Column
    private String detailDescription; //자세한 설명

    @Column
    private int back; //백엔드

    @Column
    private int front; //프론트

    @Column
    private int pm; //pm

    @Column
    private int design; //디자인

    @Column
    private int eta; //예상 개발 기간

    @Column
    private LocalDateTime recruitmentEndDate; // 모집 마감 날짜

    @Column
    private boolean isRecruited;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Application> applications;

    @Builder
    public Project(String name,
                   String shortDescription,
                   String detailDescription,
                   int back,
                   int front,
                   int pm,
                   int design,
                   int eta,
                   boolean isRecruited,
                   LocalDateTime recruitmentEndDate,
                   User creator
    ) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.detailDescription = detailDescription;
        this.back = back;
        this.front = front;
        this.pm = pm;
        this.design = design;
        this.eta = eta;
        this.isRecruited = isRecruited;
        this.recruitmentEndDate = recruitmentEndDate;
        this.creator = creator;
    }

    public void updateProject(String name,
                              String shortDescription,
                              String detailDescription,
                              int back,
                              int front,
                              int pm,
                              int design
    ) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.detailDescription = detailDescription;
        this.back = back;
        this.front = front;
        this.pm = pm;
        this.design = design;
    }

    public void updateIsRecruited(boolean endRecruiting) {
        this.isRecruited = endRecruiting;
    }
}
