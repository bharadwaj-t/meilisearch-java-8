package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meilisearch.sdk.Index;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class Controller {
    @Autowired
    Index index;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(value = "/add")
    public void addDoc(@RequestBody Document document) throws Exception {
        String data = objectMapper.writeValueAsString(document);
        String secondData = "[" + data + "]";
        log.info(secondData);
        index.addDocuments(secondData);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<String> getDoc(@RequestParam String id) throws Exception {
        return new ResponseEntity<>(index.getDocument(id), HttpStatus.OK);
    }
}
