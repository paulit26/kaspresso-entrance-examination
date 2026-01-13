package ru.webrelab.kie.cerealstorage

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CerealStorageImplTest {

    private val storage = CerealStorageImpl(10f, 20f)

    // getAmount()
    // Возвращает 0 при добавлении нового типа крупы
    @Test
    fun getAmount_1() {
        assertEquals(0f, storage.getAmount(Cereal.BULGUR), 0.1f)
        assertEquals(0f, storage.getAmount(Cereal.RICE), 0.1f)
    }
    // Возвращает актуальное количество крупы при добавлении
    @Test
    fun getAmount_2() {
        storage.addCereal(Cereal.BULGUR, 9.0f)
        assertEquals(9.0f, storage.getAmount(Cereal.BULGUR), 0.1f)
        storage.addCereal(Cereal.BULGUR, 0.4f)
        assertEquals(9.4f, storage.getAmount(Cereal.BULGUR), 0.1f)
    }


    // addCereal()
    // Проверяем на минус
    @Test
    fun addCereal_1() {
        assertThrows<IllegalArgumentException> {
            storage.addCereal(Cereal.BULGUR, -0.1f)
        }
    }

    // Добавляем 0 количества крупы и проверяем, что ничего не поменялось
    @Test
    fun addCereal_2() {
        val rem = storage.addCereal(Cereal.BULGUR, 0f)
        assertEquals(0f, rem)
        assertEquals(0f, storage.getAmount(Cereal.BULGUR))
    }

    // Переполняем контейнер крупой и проверяем остаток
    @Test
    fun addCereal_3() {
        storage.addCereal(Cereal.BULGUR, 6.0f)
        val remain = storage.addCereal(Cereal.BULGUR, 7.0f)
        assertEquals(3.0f, remain, 0.1f)
        assertEquals(10.0f, storage.getAmount(Cereal.BULGUR), 0.1f)
        assertEquals(5.0f, storage.addCereal(Cereal.BULGUR, 5.0f), 0.1f)
    }

    // Добавляем новый тип крупы и проверяем, что все влезло
    @Test
    fun addCereal_4() {
        val remain = storage.addCereal(Cereal.BULGUR, 9.3f)
        assertEquals(0f, remain, 0.1f)
        assertEquals(9.3f, storage.getAmount(Cereal.BULGUR), 0.1f)
    }
}