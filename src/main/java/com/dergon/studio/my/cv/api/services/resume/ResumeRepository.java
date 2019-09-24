package com.dergon.studio.my.cv.api.services.resume;

import com.dergon.studio.my.cv.api.models.Resume;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Damian L. Lisas on 2019-08-13
 */
public interface ResumeRepository extends CrudRepository<Resume, Long> {

    @Transactional
    Optional<Resume> findByName(String fileName);
}
