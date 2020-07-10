package com.group1.EnglishApp.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

/**
 * @author Hai Dang
 */
@Entity
@Table(name = "Role", schema = "EnglishApp")
public class Role extends BaseEntity implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Column(name = "Name")
    private String name;

    @Column(name = "DisplayName")
    private String displayName;

    @Column(name = "Description")
    private String description;

    @OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "RolePermission", schema = "EnglishApp",
            joinColumns = @JoinColumn(name = "RoleId", foreignKey = @ForeignKey(name = "FK_RolePermission_RoleId")),
            inverseJoinColumns = @JoinColumn(name = "PermissionId", foreignKey = @ForeignKey(name = "FK_RolePermission_PermissionId")))
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Permission> permissions;

    public Role() {
        // do nothing
    }

    public Role(String role) {
        this.name = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Transient
    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(name, role.name)
                .append(displayName, role.displayName)
                .append(description, role.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(name)
                .append(displayName)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("name", name)
                .append("displayName", displayName)
                .append("description", description)
                .toString();
    }
}
