package com.yzdev.sportome

import com.yzdev.sportome.common.dateProcess.getDifferenceAge
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
        assertEquals(10, getDifferenceAge(ageTest = 28))
    }
}