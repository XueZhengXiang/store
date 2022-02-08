package com.project.store.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@ToString
@Data
public class User extends BaseEntity implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(salt, user.salt) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(gender, user.gender) && Objects.equals(avatar, user.avatar) && Objects.equals(isDelete, user.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uid, username, password, salt, phone, email, gender, avatar, isDelete);
    }
}
