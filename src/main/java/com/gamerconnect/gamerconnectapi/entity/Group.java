package com.gamerconnect.gamerconnectapi.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String title;

    private String description;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private Boolean isPublic;

    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "groups")
    private Set<User> members;

}
