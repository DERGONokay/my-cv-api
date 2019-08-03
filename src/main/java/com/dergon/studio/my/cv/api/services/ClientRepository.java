package com.dergon.studio.my.cv.api.services;

import com.dergon.studio.my.cv.api.models.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Damian L. Lisas on 2019-08-02
 */
public interface ClientRepository extends CrudRepository<Client, Long> { }
