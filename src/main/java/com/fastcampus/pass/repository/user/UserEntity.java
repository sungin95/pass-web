package com.fastcampus.pass.repository.user;

import com.fastcampus.pass.repository.BaseEntity;
import com.fastcampus.pass.repository.user.constant.RoleType;
import com.fastcampus.pass.repository.user.converter.RoleTypesConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(
        name = "user",
        indexes = {
                @Index(columnList = "email", unique = true),
                @Index(columnList = "createdAt"),
                @Index(columnList = "createdBy")
        }
)
public class UserEntity extends BaseEntity {
    @Id
    @Column(length = 20)
    private String userId;

    @Setter
    @Column(nullable = false, length = 100)
    private String userPassword;

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 50)
    private String nickname;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Convert(converter = RoleTypesConverter.class)
    @Column(nullable = false)
    private Set<RoleType> roleTypes = new LinkedHashSet<>();

    private String phone;

    private String meta;


    protected UserEntity() {
    }

    public UserEntity(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone, String meta, String createdBy) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
        this.roleTypes = roleTypes;
        this.phone = phone;
        this.meta = meta;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }


    public static UserEntity of(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone) {
        return UserEntity.of(userId, userPassword, email, nickname, status, roleTypes, phone, userId);
    }

    public static UserEntity of(String userId, String userPassword, String email, String nickname, UserStatus status, Set<RoleType> roleTypes, String phone, String createdBy) {
        return new UserEntity(userId, userPassword, email, nickname, status, roleTypes, phone, null, createdBy);
    }

    public void addRoleType(RoleType roleType) {
        this.getRoleTypes().add(roleType);
    }

    public void addRoleTypes(Collection<RoleType> roleTypes) {
        this.getRoleTypes().addAll(roleTypes);
    }

    public void removeRoleType(RoleType roleType) {
        this.getRoleTypes().remove(roleType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
