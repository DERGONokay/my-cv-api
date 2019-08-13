package com.dergon.studio.my.cv.api.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Calendar;

/**
 * @author Damian L. Lisas on 2019-08-13
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "CREATE_T", nullable = false)
    Calendar createTimestamp;

    @LastModifiedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_T")
    Calendar updateTimestamp;
}
