package com.dergon.studio.my.cv.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

/**
 * @author Damian L. Lisas on 2019-08-13
 */
@Getter
@Setter
@Entity
@Table(name = "RESUME")
public class Resume extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FORMAT", nullable = false)
    private String format;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "FILE")
    private Blob file;

    @Column(name = "VERSION")
    private int version;
}
