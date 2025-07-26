package com.app.admin_backend_v1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ProjectAmenityId implements Serializable {
    private static final long serialVersionUID = -8160044527489056545L;
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "amenity_id", nullable = false)
    private Integer amenityId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjectAmenityId entity = (ProjectAmenityId) o;
        return Objects.equals(this.amenityId, entity.amenityId) &&
                Objects.equals(this.projectId, entity.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amenityId, projectId);
    }

}