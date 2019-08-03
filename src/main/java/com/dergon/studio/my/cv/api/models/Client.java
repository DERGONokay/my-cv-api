package com.dergon.studio.my.cv.api.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "USER")
public class Client {

    public Client() {
        super();
    }

    public Client(String email, Calendar downloadTimestamp) {
        this.email = email;
        this.downloadTimestamp = downloadTimestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DOWNLOAD_TIMESTAMP")
    private Calendar downloadTimestamp;
}
