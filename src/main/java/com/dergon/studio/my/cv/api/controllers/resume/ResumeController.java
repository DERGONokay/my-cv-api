package com.dergon.studio.my.cv.api.controllers.resume;

import com.dergon.studio.my.cv.api.exceptions.InvalidRequestException;
import com.dergon.studio.my.cv.api.models.Resume;
import com.dergon.studio.my.cv.api.services.client.ClientService;
import com.dergon.studio.my.cv.api.services.resume.ResumeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * @author Damian L. Lisas on 2019-08-13
 */
@Controller
@RequestMapping("/api/v1/resumes")
public class ResumeController {

    @Autowired private ResumeService service;
    @Autowired private ClientService clientService;


    @PostMapping("/save")
    @ApiOperation(value = "Saves the given file in the database, if a file already exists with the same name it gets updated and its version is increased.")
    @CrossOrigin
    public ResponseEntity<Resume> save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("format") String format
    ) throws IOException {
        Resume resume = service.findByName(file.getOriginalFilename());

        if(resume == null) {
            resume = new Resume();
            resume.setFormat(format);
            resume.setData(file.getBytes());
            resume.setName(file.getOriginalFilename());
            resume.setVersion(1);
        } else {
            resume.increaseVersion();
        }

        return ResponseEntity.ok(service.save(resume));
    }

    @GetMapping("/{resumeName}")
    @ApiOperation(value = "Get a resume by its name")
    @CrossOrigin
    public ResponseEntity get(
            @PathVariable(value = "resumeName") String fileName,
            @RequestParam(value = "email") String email
    ) {
        Resume resume = service.findByName(fileName);
        ByteArrayResource resource = null;
        if(isNull(resume)) {
            ResponseEntity.notFound();
        }


        try {
            clientService.save(email);
        } catch (InvalidRequestException e) {
            ResponseEntity.badRequest();
        }

        if(resume != null) {
            resource = new ByteArrayResource(resume.getData());
        }

        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
