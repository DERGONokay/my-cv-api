package com.dergon.studio.my.cv.api.services.resume;

import com.dergon.studio.my.cv.api.models.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Damian L. Lisas on 2019-08-13
 */
@Service
public class ResumeService {

    @Autowired
    private ResumeRepository repository;

    public Resume save(Resume resume) {
        return repository.save(resume);
    }

    public Resume findByName(String fileName) {
        return repository.findByName(fileName).orElse(null);
    }
}
