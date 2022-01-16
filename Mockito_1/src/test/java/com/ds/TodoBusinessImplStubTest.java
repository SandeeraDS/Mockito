package com.ds;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoBusinessImplStubTest {

    static TodoBusinessImpl todoBusiness;

    @Test
    @BeforeAll
     static void init(){
        TodoService todoService = new TodoServiceStub();
        todoBusiness = new TodoBusinessImpl(todoService);
    }

    @Test
    void retrieveTodosRelatedToSpringTest(){
        List<String> filteredList = todoBusiness.retrieveTodosRelatedToSpring("dummy");
        assertEquals(2,filteredList.size());
    }
}
