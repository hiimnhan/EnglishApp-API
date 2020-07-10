package com.group1.EnglishApp.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author Hai Dang
 */
@Entity
@Table(name = "Permission", schema = "EnglishApp")
public class Permission extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @ManyToMany
    @JoinTable(name = "RolePermission", schema = "EnglishApp",
            joinColumns = @JoinColumn(name = "PermissionId", foreignKey = @ForeignKey(name = "FK_RolePermission_PermissionId")),
            inverseJoinColumns = @JoinColumn(name = "RoleId", foreignKey = @ForeignKey(name = "FK_RolePermission_RoleId")))
    private Set<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(name, that.name)
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(name)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("name", name)
                .append("description", description)
                .toString();
    }
}
