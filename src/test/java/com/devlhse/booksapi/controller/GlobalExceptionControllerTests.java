package com.devlhse.booksapi.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionControllerTests {

    private Exception e;

    @InjectMocks
    GlobalExceptionController globalExceptionController;

    @Before
    public void setUp(){
        e = new Exception();
    }

    @Test
    public void shouldHandleNotFoundExceptionWithSuccess() {
        Assert.assertNotNull(globalExceptionController.handleNotFoundException(e));
    }

    @Test
    public void shouldHandleFieldEmptyExceptionWithSuccess() {
        Assert.assertNotNull(globalExceptionController.handleFieldEmptyException(e));
    }
}
