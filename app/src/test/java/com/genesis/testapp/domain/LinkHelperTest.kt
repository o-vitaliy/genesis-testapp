package com.genesis.testapp.domain

import junit.framework.Assert.assertEquals
import org.junit.Test

class LinkHelperTest {
    @Test
    fun getParamFromGivenLink() {
        val param = "code"
        val expected = "3278eed02994b94172ee"
        val link = "genesis://auth?$param=$expected"

        val result = LinkHelper.getParam(link, param)
        assertEquals(expected, result)
    }

    @Test
    fun getParamFromNullLink() {
        val param = "code"
        val expected = null
        val link = null

        val result = LinkHelper.getParam(link, param)
        assertEquals(expected, result)
    }

    @Test
    fun getParamFromNoParamLink() {
        val param = "code"
        val expected = null
        val link = "genesis://auth?"

        val result = LinkHelper.getParam(link, param)
        assertEquals(expected, result)
    }
}