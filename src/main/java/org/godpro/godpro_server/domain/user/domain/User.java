package org.godpro.godpro_server.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.godpro.godpro_server.domain.application.domain.Application;
import org.godpro.godpro_server.domain.project.domain.Project;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String kakaoId;

    @Column
    private String profileImageUrl;

    @Column
    private String profileFileName;

    @Column
    private String githubUrl;

    @Column
    private String portfolioUrl;

    @Column
    private String introduction;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Project> projects;

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Application> applications;

    @Builder
    public User(String name, String kakaoId, String profileImageUrl, String profileFileName, String githubUrl, String portfolioUrl, String introduction) {
        this.name = name;
        this.kakaoId = kakaoId;
        this.profileImageUrl = profileImageUrl;
        this.profileFileName = profileFileName;
        this.githubUrl = githubUrl;
        this.portfolioUrl = portfolioUrl;
        this.introduction = introduction;
    }
}
