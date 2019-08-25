package com.dergon.studio.my.cv.api.controllers.resume;

import com.dergon.studio.my.cv.api.controllers.resume.dto.SaveResumeRequest;
import com.dergon.studio.my.cv.api.exceptions.InvalidRequestException;
import com.dergon.studio.my.cv.api.models.Resume;
import com.dergon.studio.my.cv.api.services.client.ClientService;
import com.dergon.studio.my.cv.api.services.resume.ResumeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * @author Damian L. Lisas on 2019-08-13
 */
@Log4j2
@CrossOrigin(origins = {"damian-lisas-cv.firebaseapp.com", "https://damian-lisas-cv.firebaseapp.com", "http://damian-lisas-cv.firebaseapp.com"})
@RestController
@RequestMapping("/api/v1/resumes")
public class ResumeController {

    @Autowired private ResumeService resumeService;
    @Autowired private ClientService clientService;


    @PostMapping(value = "/save")
    @ApiOperation(value = "Saves the given file in the database, if a file already exists with the same name it gets updated and its version is increased.")
    public ResponseEntity<Resume> save(
            @ModelAttribute SaveResumeRequest body
    ) throws IOException {
        log.info("New save Resume request.");
        Resume resume = resumeService.findByName(body.getFile().getOriginalFilename());

        if(isNull(resume)) {
            log.info("Resume doesn't exists, creating a new one.");
            resume = new Resume();
            resume.setFormat(body.getFormat());
            resume.setData(body.getFile().getBytes());
            resume.setName(body.getFile().getOriginalFilename());
            resume.setVersion(1);
        } else {
            log.info("Resume already exists, increasing version.");
            resume.increaseVersion();
        }

        log.info("Saving resume.");
        resume = resumeService.save(resume);
        log.info("Request processed normally.");
        return ResponseEntity.status(HttpStatus.CREATED).body(resume);
    }

    @GetMapping("/{resumeName}")
    @ApiOperation(value = "Get a resume by its name")
    public ResponseEntity get(
            @PathVariable(value = "resumeName") String fileName,
            @RequestParam(value = "email") String email
    ) {
        log.info("Getting resume with name " + fileName + ". Requester = " + email + ".");
        Resume resume = resumeService.findByName(fileName);
        if(isNull(resume)) {
            log.error("The requested resume doesn't exists.");
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(resume.getData());

        try {
            log.info("Saving client.");
            clientService.save(email);
            log.info("Client saved!");
        } catch (InvalidRequestException e) {
            log.error("Invalid client. Returning status " + HttpStatus.BAD_REQUEST + ".");
            return ResponseEntity.badRequest().build();
        }

        log.info("Request processed normally.");
        return ResponseEntity.ok()
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
