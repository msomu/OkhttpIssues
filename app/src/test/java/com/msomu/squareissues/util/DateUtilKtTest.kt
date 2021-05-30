package com.msomu.squareissues.util

import org.junit.Test
import com.google.common.truth.Truth.assertThat

class DateUtilKtTest{


    @Test
    fun `if the string is not in the required format should return null`(){
        val actualDate = "12-01-2022".toDate()
        val expectedDate = null
        assertThat(actualDate).isEqualTo(expectedDate)
    }

    @Test
    fun `if the string is in the required format should return correct value`(){
        val actualDate = "2021-05-25T10:02:12Z".toDate()
        val expectedDate = "05-25-2021"
        assertThat(actualDate).isEqualTo(expectedDate)
    }

}