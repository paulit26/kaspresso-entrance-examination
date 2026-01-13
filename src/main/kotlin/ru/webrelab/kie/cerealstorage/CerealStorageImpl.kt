package ru.webrelab.kie.cerealstorage

class CerealStorageImpl(
    override val containerCapacity: Float,
    override val storageCapacity: Float
) : CerealStorage {

    init {
        require(containerCapacity >= 0) {
            "Ёмкость контейнера не может быть отрицательной"
        }
        require(storageCapacity >= containerCapacity) {
            "Ёмкость хранилища не должна быть меньше ёмкости одного контейнера"
        }
    }

    private val storage = mutableMapOf<Cereal, Float>()
    override fun addCereal(cereal: Cereal, amount: Float): Float {
       return 0f
    }

    override fun getCereal(cereal: Cereal, amount: Float): Float {
        return 0f
    }

    override fun removeContainer(cereal: Cereal): Boolean {
      return false
    }

    override fun getAmount(cereal: Cereal): Float {
        return 0f
    }

    override fun getSpace(cereal: Cereal): Float {
        return 0f
    }

    override fun toString(): String {
      return ""
    }

}
