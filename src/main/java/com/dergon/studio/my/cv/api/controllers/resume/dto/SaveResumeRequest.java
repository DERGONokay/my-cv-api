package com.dergon.studio.my.cv.api.controllers.resume.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author Damian L. Lisas on 2019-08-24
 */
@Getter
@Setter
public class SaveResumeRequest implements Serializable {

    private MultipartFile file;
    private String format;

    public SaveResumeRequest(MultipartFile file, String format) {
        setFile(file);
        setFormat(format);
    }
}
