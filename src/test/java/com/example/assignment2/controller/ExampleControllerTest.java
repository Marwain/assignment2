package com.example.assignment2.controller;

import com.example.assignment2.domain.Example;
import com.example.assignment2.repository.ExampleRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExampleControllerTest {

    @InjectMocks
    private ExampleController exampleControllerFixture;

    @Mock
    private ExampleRepository exampleRepositoryMock;

    private final Example example = new Example();
    private final long ID = 1;
    private final String TITLE = "title";
    private final String DESCRIPTION = "description";

    @Before
    public void setUp() {
        example.setId(ID);
        example.setTitle(TITLE);
        example.setDescription(DESCRIPTION);

        when(exampleRepositoryMock.findById(ID))
                .thenReturn(java.util.Optional.of(example));
    }

    @Test
    public void publishToDBNewComponentStandardTest() {
        ResponseEntity<Example> response = exampleControllerFixture.getExampleById(ID);

        assertEquals("<200 OK OK,Example [id=1, title=title, desc=description],{}>", response.toString());

        verify(exampleRepositoryMock).findById(ID);
    }
}
