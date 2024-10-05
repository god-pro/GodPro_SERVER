package org.godpro.godpro_server.domain.application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.godpro.godpro_server.domain.project.domain.Project;
import org.godpro.godpro_server.domain.user.domain.User;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String motivation;

    @Column(nullable = false)
    private String contact; // 면접자와 컨택할 링크, 전화번호 등등

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PartType part; // 지원할 파트

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonBackReference
    private Project project;

    public Application(String motivation, String contact, String part, User user, Project project) {
        this.motivation = motivation;
        this.contact = contact;
        this.part = PartType.valueOf(part);
        this.user = user;
        this.project = project;
    }
}

