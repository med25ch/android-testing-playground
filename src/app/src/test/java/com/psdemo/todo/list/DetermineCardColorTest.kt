package com.psdemo.todo.list

import com.psdemo.todo.R
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DetermineCardColorTest {

    // We gonna use methodName_scenario_expectedResult

    companion object {
        private val now = System.currentTimeMillis()
        private const val day = 1000L * 60 * 60 * 24

        @JvmStatic
        fun dueDateCases() = Stream.of(
            Arguments.of(now - day, R.color.todoOverDue),
            Arguments.of(now + (7 * day), R.color.todoDueStrong),
            Arguments.of(now + (14 * day), R.color.todoDueMedium),
            Arguments.of(now + (16 * day), R.color.todoDueLight),
        )
    }

    @Test
    fun determineCardColor_doneIsTrue_colorIsToDoDone() {
        assertEquals(R.color.todoDone, determineCardColor(null, true))
    }

    @Test
    fun determineCardColor_dueDateIsNull_colorIsToDoNotDue() {
        assertEquals(R.color.todoNotDue, determineCardColor(null, false))
    }

    @ParameterizedTest
    @MethodSource("dueDateCases")
    fun determineCardColor_doneIsFalse_colorMatchesDueDate(
        dueDate: Long,
        expected: Int
    ) {
        val result = determineCardColor(dueDate, done = false)
        assertEquals(expected, result)
    }


}