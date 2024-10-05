package org.godpro.godpro_server.domain.application.domain;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Application(String motivation, String contact, String part, User user, Project project) {
        this.motivation = motivation;
        this.contact = contact;
        this.part = PartType.valueOf(part);
        this.user = user;
        this.project = project;
    }
}
