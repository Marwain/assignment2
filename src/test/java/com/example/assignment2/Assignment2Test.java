package com.example.assignment2;

import com.example.assignment2.domain.Example;
import com.example.assignment2.repository.ExampleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Assignment2Test {
    @Autowired
    private ExampleRepository repository;

    @Test
    public void shouldFindAllExamples() {
        Iterable<Example> examples = repository.findAll();

        int size = 5;
        assertThat(examples).hasSize(size);
    }
}
