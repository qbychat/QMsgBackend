package org.cubewhy.chat.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Account implements BaseData, Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    private String nickname;
    private String email;
    private String bio;

    private LocalDateTime registerTime;
    private LocalDateTime updatedTime;

    @PrePersist
    protected void onCreate() {
        this.registerTime = LocalDateTime.now();
        this.updatedTime = registerTime;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user")
    private List<ChannelUser> channelUsers;

    @Override
    public String getName() {
        return username;
    }

    public Set<Permission> getPermissions() {
        Set<Permission> permissions = new HashSet<>();
        for (Role role : roles) {
            permissions.addAll(role.getPermissions());
        }
        return permissions;
    }
}
