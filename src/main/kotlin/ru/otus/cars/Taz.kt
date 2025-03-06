package ru.otus.cars

object Taz: Car {
    override val tank: Tank = TazTank()
    override val carOutput: CarOutput = TazOutput()
    /**
     * Номерной знак
     */
    override val plates: Car.Plates
        get() = throw NotImplementedError("Номера сняты")

    /**
     * Цвет машины
     */
    override val color: String = "Ржавый"


    /**
     * Получить оборудование
     */
    override fun getEquipment(): String = "Крыса"

    /**
     * Руль вправо на [degrees] градусов
     */
    override fun wheelToRight(degrees: Int) {
        throw NotImplementedError("Руля нет")
    }

    /**
     * Руль влево на [degrees] градусов
     */
    override fun wheelToLeft(degrees: Int) {
        throw NotImplementedError("Руля нет")
    }

    class TazOutput : CarOutput {
        override fun getCurrentSpeed(): Int {
            throw NotImplementedError("Спидометр недоступен")
        }

        override fun getFuelLevel(): Int {
            return tank.getContents()
        }
    }

    class TazTank : Tank {
        override var capacity : Int = 0
        override var fuelLevel : Int = 0
        override var mouth : TankMouth? = PetrolMouth(this)
    }
}