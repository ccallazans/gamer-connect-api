package com.gamerconnect.gamerconnectapi.matchmaking.entity;

import com.gamerconnect.gamerconnectapi.account.entity.User;
import com.gamerconnect.gamerconnectapi.matchmaking.entity.Group;
import jakarta.persistence.*;

@Entity
@Table(name = "group_members")
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private User member;
}
