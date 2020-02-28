package com.example.assignment2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.assignment2.domain.Example;
import com.example.assignment2.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ExampleController {

    @Autowired
    ExampleRepository exampleRepository;

    @GetMapping("/examples")
    public ResponseEntity<List<Example>> getAllExamples(@RequestParam(required = false) String title) {
        try {
            List<Example> examples = new ArrayList<>();

            if (title == null)
                examples.addAll(exampleRepository.findAll());
            else
                examples.addAll(exampleRepository.findByTitleContaining(title));
                //exampleRepository.findByTitleContaining(title).forEach(example -> examples.add(example));
                //exampleRepository.findByTitleContaining(title).forEach(examples::add);

            if (examples.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(examples, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/examples/{id}")
    public ResponseEntity<Example> getExampleById(@PathVariable("id") long id) {
        Optional<Example> exampleData = exampleRepository.findById(id);
//        if (exampleData.isPresent()) {
//            return new ResponseEntity<>(exampleData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return exampleData.map(example -> new ResponseEntity<>(example, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/examples")
    public ResponseEntity<Example> createExample(@RequestBody Example example) {
        try {
            Example _example = exampleRepository
                    .save(new Example(example.getTitle(), example.getDescription()));
            return new ResponseEntity<>(_example, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/examples/{id}")
    public ResponseEntity<Example> updateExample(@PathVariable("id") long id, @RequestBody Example example) {
        Optional<Example> exampleData = exampleRepository.findById(id);

        if (exampleData.isPresent()) {
            Example _example = exampleData.get();
            _example.setTitle(example.getTitle());
            _example.setDescription(example.getDescription());
            return new ResponseEntity<>(exampleRepository.save(_example), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/examples/{id}")
    public ResponseEntity<HttpStatus> deleteExample(@PathVariable("id") long id) {
        try {
            exampleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/examples")
    public ResponseEntity<HttpStatus> deleteAllExamples() {
        try {
            exampleRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
