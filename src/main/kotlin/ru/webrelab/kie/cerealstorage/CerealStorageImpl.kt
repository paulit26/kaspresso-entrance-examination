package ru.webrelab.kie.cerealstorage

fun main() {
    val storage = CerealStorageImpl(10f, 20f)
    storage.addCereal(Cereal.RICE, 4.2f)
    storage.addCereal(Cereal.BULGUR, 6.8f)
    storage.addCereal(Cereal.BULGUR, 1.2f)
    storage.getCereal(Cereal.RICE,1.0f)
    print(storage)
}

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
    private val total: Float
        get() = storage.values.sum()

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
        when {
            amount <= 0 -> throw IllegalStateException("")
            amount == 0f -> return 0f
        }
        val current = storage[cereal] ?: return 0f
        val taken = minOf(amount, current)
        storage[cereal] = current - taken
        return taken
    }

    override fun removeContainer(cereal: Cereal): Boolean {
        val amount = storage[cereal] ?: return false
        return when {
            amount == 0f -> {
                storage.remove(cereal)
                true
            }

            else -> false
        }
    }

    override fun getAmount(cereal: Cereal) = storage[cereal] ?: 0f

    override fun getSpace(cereal: Cereal): Float {
        val current = storage[cereal] ?: throw IllegalStateException("Нет контейнера для крупы: $cereal")
        return containerCapacity - current
    }

    override fun toString(): String {
        val lines = storage.map { (cereal, amount) -> "${cereal.name}: $amount / $containerCapacity" }.sorted()
        return buildString {
            append("CerealStorage: $total / $storageCapacity\n")
            if (lines.isNotEmpty()) {
                append(lines.joinToString("\n"))
            } else {
                append(" (empty)")
            }
        }
    }

}
