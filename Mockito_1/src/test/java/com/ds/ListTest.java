package com.ds;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    void letsMockListSizeMethod(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2,listMock.size());

    }

    @Test
    void letsMockListSize_ReturnMultipleValues(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);

        assertEquals(2,listMock.size());
        assertEquals(3,listMock.size());

    }

    @Test
    void letsMockListGetMethod(){
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("Sandeera");

        assertEquals("Sandeera",listMock.get(0));
        assertNull(listMock.get(1)); // default value null;
    }

    @Test
    void letsMockListGetMethod_AnyInt(){
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenReturn("Sandeera");

        assertEquals("Sandeera",listMock.get(0));
        assertEquals("Sandeera",listMock.get(1));
    }

    @Test
    void letsMockListGetMethod_ThrowException() {
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));

        assertThrows(RuntimeException.class,()->listMock.get(0));
    }
}
