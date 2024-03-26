package com.gamerconnect.gamerconnectapi.matchmaking.entity;

import com.gamerconnect.gamerconnectapi.account.entity.User;
import com.gamerconnect.gamerconnectapi.matchmaking.entity.Game;
import jakarta.persistence.*;
import lombok.*;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "groups")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "public")
    private boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;


    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
