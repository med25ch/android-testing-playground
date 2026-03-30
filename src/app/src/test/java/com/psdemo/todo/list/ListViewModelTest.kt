package com.psdemo.todo.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.psdemo.todo.data.Todo
import com.psdemo.todo.data.TodoRepository
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ListViewModelTest {

    @Test
    fun allTodos_whenListIsEmpty(){

        val expected = 0
        val repository : TodoRepository = mockk(relaxed = true)

        every { repository.getAllTodos() } returns MutableLiveData(emptyList())

        val listViewModel = ListViewModel(repository)

        val todos = listViewModel.allTodos.value

        assertEquals(expected, todos!!.size)

    }

    @Test
    fun allTodos_whenListIsNotEmpty(){

        val expected = 2
        val repository : TodoRepository = mockk(relaxed = true)

        val todo1 = Todo(
            id = "1",
            title = "Buy groceries",
            dueDate = 1711807200000L,
            completed = false,
            created = 1711720800000L
        )

        val todo2 = Todo(
            id = "2",
            title = "Fix unit tests",
            dueDate = null,
            completed = true,
            created = 1711720800000L
        )


        every { repository.getAllTodos() } returns MutableLiveData(listOf(todo1,todo2))

        val listViewModel = ListViewModel(repository)

        val todos = listViewModel.allTodos.value

        assertEquals(expected, todos!!.size)

    }

    @Test
    fun upcomingTodosCount_whenListIsEmpty(){
        val expected = 0
        val repository : TodoRepository = mockk(relaxed = true)

        every { repository.getUpcomingTodosCount() } returns MutableLiveData(0)

        val listViewModel = ListViewModel(repository)

        val count = listViewModel.upcomingTodosCount.value

        assertEquals(expected, count)

    }

}