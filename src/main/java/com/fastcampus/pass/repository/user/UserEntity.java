package com.fastcampus.pass.repository.user;

import com.fastcampus.pass.repository.BaseEntity;
import com.fastcampus.pass.repository.user.constant.RoleType;
import com.fastcampus.pass.repository.user.converter.RoleTypesConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
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
    @Column(nullable = false, length = 20)
    private String userPassword;

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 50)
    private String userName;


    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Convert(converter = RoleTypesConverter.class)
    @Column(nullable = false)
    private Set<RoleType> roleTypes = new LinkedHashSet<>();

    private String phone;

    private Long remainingDaysAtGym;

    private String meta;

    protected UserEntity() {
    }

    private UserEntity(String userId, String userPassword, String email, String userName, UserStatus status, String phone, Long remainingDaysAtGym, String meta) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.userName = userName;
        this.status = status;
        this.phone = phone;
        this.remainingDaysAtGym = remainingDaysAtGym;
        this.meta = meta;
    }

    public static UserEntity of(String userId, String userPassword, String email, String userName, UserStatus status, String phone, Long remainingDaysAtGym) {
        return new UserEntity(userId, userPassword, email, userName, status, phone, remainingDaysAtGym, "");
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

}
