package com.dergon.studio.my.cv.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    @Lob
    @Column(name = "DATA")
    private byte[] data;

    @Column(name = "VERSION")
    private int version;

    public void increaseVersion() {
        this.version++;
    }
}
