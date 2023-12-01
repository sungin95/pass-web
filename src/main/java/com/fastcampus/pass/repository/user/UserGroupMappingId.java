package com.fastcampus.pass.repository.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
public class UserGroupMappingId implements Serializable {
    private String userGroupId;
    private String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroupMappingId that)) return false;
        return Objects.equals(userGroupId, that.userGroupId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userGroupId, userId);
    }
}
