package com.app.admin_backend_v1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "area", schema = "real_estate_mvp")
public class Area {
    @Id
    @Column(name = "area_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_district_id")
    private SubDistrict subDistrict;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "pincode", length = 10)
    private String pincode;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

}