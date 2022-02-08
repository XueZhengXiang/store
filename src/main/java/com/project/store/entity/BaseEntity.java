package com.project.store.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@ToString
@Data
public class BaseEntity implements Serializable {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(createdUser, that.createdUser) && Objects.equals(createdTime, that.createdTime) && Objects.equals(modifiedUser, that.modifiedUser) && Objects.equals(modifiedTime, that.modifiedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdUser, createdTime, modifiedUser, modifiedTime);
    }
}
