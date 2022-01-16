package com.ds;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

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

    @Test
    void deleteTodosNotRelatedToSpring_UsingBDD(){

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to Dance");
        given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        // When
        todoBusiness.deleteTodosNotRelatedToSpring("dummy");

        // Then
        verify(todoServiceMock).deleteTodo("Learn to Dance"); // success
        //verify(todoServiceMock).deleteTodo("Learn Spring"); // failed

        //verify(todoServiceMock,never()).deleteTodo("Learn to Dance"); // failed
        verify(todoServiceMock,never()).deleteTodo("Learn Spring"); // success

        verify(todoServiceMock,times(1)).deleteTodo("Learn to Dance"); // success
        //verify(todoServiceMock,times(2)).deleteTodo("Learn to Dance"); // failed
    }

    @Test
    void deleteTodosNotRelatedToSpring_UsingBDD_Then(){

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to Dance");
        given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        // When
        todoBusiness.deleteTodosNotRelatedToSpring("dummy");

        // Then
        //verify(todoServiceMock).deleteTodo("Learn to Dance"); // success
        then(todoServiceMock).should().deleteTodo("Learn to Dance");

        //verify(todoServiceMock,never()).deleteTodo("Learn Spring"); // success
        then(todoServiceMock).should(never()).deleteTodo("Learn Spring");

        //verify(todoServiceMock,times(1)).deleteTodo("Learn to Dance"); // success
        then(todoServiceMock).should(times(1)).deleteTodo("Learn to Dance");
    }

    @Test
    void deleteTodosNotRelatedToSpring_ArgumentCapture(){

        // Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        // Define Argument Captor on specific method call
        // Capture the argument

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to Dance");
        given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        // When
        todoBusiness.deleteTodosNotRelatedToSpring("dummy");

        // Then
        then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
        assertEquals(stringArgumentCaptor.getValue(),"Learn to Dance");
    }

    @Test
    void deleteTodosNotRelatedToSpring_ArgumentCaptureMultipleTimes(){

        // Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        // Define Argument Captor on specific method call
        // Capture the argument

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn to Rock and Roll","Learn Spring","Learn to Dance");
        given(todoServiceMock.retrieveTodos("dummy")).willReturn(todos);
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoServiceMock);

        // When
        todoBusiness.deleteTodosNotRelatedToSpring("dummy");

        // Then
        then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
        assertEquals(stringArgumentCaptor.getAllValues().size(),2);
    }
}
