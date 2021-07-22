package com.rudikov.catalog.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode(of = {"id", "role"})
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<Moderator> moderators;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Moderator> getModerators() {
        return moderators;
    }

    public void setModerators(Set<Moderator> moderators) {
        this.moderators = moderators;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
