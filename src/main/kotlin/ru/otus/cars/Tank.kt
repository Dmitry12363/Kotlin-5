package ru.otus.cars

class PetrolMouth(owner: Tank) : TankMouth(owner){
    fun fuelPetrol(liters :Int): Unit {
        if (!opened) throw NotImplementedError("Бак закрыт, заправка невозможна")
        owner.receiveFuel(liters)
    }
}

class LpgMouth(owner: Tank): TankMouth(owner) {
    fun fuelLpg(liters :Int): Unit {
        if (!opened) throw NotImplementedError("Бак закрыт, заправка невозможна")
        owner.receiveFuel(liters)
    }
}

abstract class TankMouth(parOwner: Tank)  {
    val owner = parOwner
    var opened : Boolean = false
    fun open(): Unit { opened = true }
    fun close(): Unit { opened = false }
}

interface Tank {
    var capacity : Int
    var fuelLevel : Int
    var mouth : TankMouth?
    fun getContents(): Int {
        return fuelLevel
    }
    fun receiveFuel(liters: Int): Unit {
        if (fuelLevel + liters > capacity) {
            fuelLevel = capacity
            throw NotImplementedError("Превышена емкость топливного бака")
        }
        fuelLevel += liters
    }
}