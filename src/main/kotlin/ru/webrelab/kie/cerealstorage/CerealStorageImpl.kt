package ru.webrelab.kie.cerealstorage

class CerealStorageImpl(
    override val containerCapacity: Float,
    override val storageCapacity: Float
) : CerealStorage {

    init {
        require(containerCapacity >= 0) {
            "Ёмкость контейнера не может быть отрицательной!"
        }
        require(storageCapacity >= containerCapacity) {
            "Ёмкость хранилища не должна быть меньше ёмкости одного контейнера!"
        }
    }

    private val storage = mutableMapOf<Cereal, Float>()

    override fun addCereal(cereal: Cereal, amount: Float): Float {
        when {
            amount < 0 -> throw IllegalArgumentException("Количество должно быть положительным!")
            amount == 0f -> return 0f
        }
        val current = storage[cereal] ?: 0f
        val spaceInContainer = containerCapacity - current
        return when {
            current > 0f -> {
                val canAdd = minOf(amount, spaceInContainer)
                storage[cereal] = current + canAdd
                amount - canAdd
            }
            else -> {
                val currentsum = storage.values.sum()
                if (currentsum + containerCapacity > storageCapacity) {
                    throw IllegalStateException("Нет места для нового контейнера в хранилище!")
                }
                // Добавляем сколько влезет в новый контейнер
                val canAdd = minOf(amount, containerCapacity)
                storage[cereal] = canAdd
                amount - canAdd
            }
        }
    }

    override fun getCereal(cereal: Cereal, amount: Float): Float {
        return 0f
    }

    override fun removeContainer(cereal: Cereal): Boolean {
      return false
    }

    override fun getAmount(cereal: Cereal) = storage[cereal] ?: 0f

    override fun getSpace(cereal: Cereal): Float {
        return 0f
    }

    override fun toString(): String {
      return ""
    }

}
