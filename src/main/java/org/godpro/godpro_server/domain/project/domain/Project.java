package org.godpro.godpro_server.domain.project.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    private int how_many; //프로젝트 인원수

    @Column
    private int how_long; //예상 개발 기간

    @Column
    private boolean isRecruited;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "project",  cascade = CascadeType.ALL, orphanRemoval = true)
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
                   int how_many,
                   int how_long,
                   boolean isRecruited,
                   User creator) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.detailDescription = detailDescription;
        this.back = back;
        this.front = front;
        this.pm = pm;
        this.design = design;
        this.how_many = how_many;
        this.how_long = how_long;
        this.isRecruited = isRecruited;
        this.creator = creator;
    }

    public void updateProject(String name,
                              String shortDescription,
                              String detailDescription,
                              int back,
                              int front,
                              int pm,
                              int design,
                              int how_many,
                              int how_long) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.detailDescription = detailDescription;
        this.back = back;
        this.front = front;
        this.pm = pm;
        this.design = design;
        this.how_many = how_many;
        this.how_long = how_long;
    }
}
