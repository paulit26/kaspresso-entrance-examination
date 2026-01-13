package ru.webrelab.kie.cerealstorage

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CerealStorageImplTest {

    private val storage = CerealStorageImpl(10f, 20f)

    @Test
    fun getAmount_0_for_new_cereal() {
        assertEquals(0f, storage.getAmount(Cereal.BULGUR), 0.001f)
        assertEquals(0f, storage.getAmount(Cereal.RICE), 0.001f)
    }

    @Test
    fun getAmount_after_add_cereal_bulgur() {
        storage.addCereal(Cereal.BULGUR, 9.0f)
        assertEquals(9.0f, storage.getAmount(Cereal.BULGUR), 0.001f)

        storage.addCereal(Cereal.BULGUR, 0.4f)
        assertEquals(9.4f, storage.getAmount(Cereal.BULGUR), 0.001f)
    }

}