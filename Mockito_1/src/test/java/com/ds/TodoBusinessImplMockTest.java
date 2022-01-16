package com.ds;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TodoBusinessImplMockTest {

    @Test
    void retrieveTodosRelatedToSpringTest(){
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to Dance");

        when(todoServiceMock.retrieveTodos("dummy")).thenReturn(todos);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);
        List<String> filteredList = todoBusiness.retrieveTodosRelatedToSpring("dummy");
        assertEquals(2,filteredList.size());
    }

    @Test
    void retrieveTodosRelatedToSpringTest_UsingBDD(){

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to Dance");
        given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        // When
        List<String> filteredList = todoBusiness.retrieveTodosRelatedToSpring("dummy");

        // Then
        assertEquals(2,filteredList.size());
    }
}
