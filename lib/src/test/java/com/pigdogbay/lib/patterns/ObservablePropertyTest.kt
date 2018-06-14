package com.pigdogbay.lib.patterns

import org.junit.Test

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.assertThat


class ObservablePropertyTest : PropertyChangedObserver<ObservablePropertyTest, ObservablePropertyTest.Veg> {

    var update = Veg.carrots
    var sender : ObservablePropertyTest? = null

    override fun update(sender: ObservablePropertyTest, update: Veg) {
        this.update = update
        this.sender = sender
    }

    enum class Veg {
        carrots, peppers, onions, chillis, mushrooms
    }

    @Test
    fun test1(){
        val obs = ObservableProperty(this, Veg.carrots)
        obs.addObserver(this)
        obs.value = Veg.chillis
        obs.removeObserver(this)
        assertThat(update, `is`(Veg.chillis))
        assertThat(sender, `is` (this))
    }

    /**
     * No change, expect no update
     */
    @Test
    fun test2(){
        val obs = ObservableProperty(this, Veg.carrots)
        obs.addObserver(this)
        obs.value = Veg.carrots
        obs.removeObserver(this)
        assertThat(update, `is`(Veg.carrots))
        assertThat(sender, nullValue())
    }
}