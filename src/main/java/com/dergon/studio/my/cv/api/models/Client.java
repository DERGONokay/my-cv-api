package com.dergon.studio.my.cv.api.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
public class Client extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "DOWNLOAD_NUMBERS")
    private int downloadNumbers;

    public void addDownload() {
        this.downloadNumbers++;
    }
}
