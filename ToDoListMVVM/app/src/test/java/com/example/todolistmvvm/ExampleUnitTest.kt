package com.example.todolistmvvm

import android.content.Context
import com.example.todolistmvvm.repository.ToDoRepository
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testCheckPositive():Unit {
        var numb = -4
        var res = ToDoRepository.checkPositive(numb)

        assertFalse("API not working correctly",res)

        numb = 4;
        res = ToDoRepository.checkPositive(numb)

        assertTrue("API not returning correct result",res)
    }
}
