package ru.otus.cars

import kotlin.random.Random

fun main() {
    val vaz1 = Togliatti.buildCar(Vaz2107, Car.Plates("123", 77))
    val vaz2 = Togliatti.buildCar(Vaz2108, Car.Plates("321", 78))
    val gs = GasStation()


    println("vaz1: в баке " + vaz1.carOutput.getFuelLevel() + " литров")
    println("vaz2: в баке " + vaz2.carOutput.getFuelLevel() + " литров")
    println("taz: в баке " + Taz.carOutput.getFuelLevel() + " литров")

    gs.car = vaz1
    gs.refuelingCar(20)
    gs.car = vaz2
    gs.refuelingCar(30)
    gs.car = Taz
    gs.refuelingCar(40)
    println("vaz1: в баке " + vaz1.carOutput.getFuelLevel() + " литров")
    println("vaz2: в баке " + vaz2.carOutput.getFuelLevel() + " литров")
    println("taz: в баке " + Taz.carOutput.getFuelLevel() + " литров")
}

fun driveCars() {
    val vaz1 = Togliatti.buildCar(Vaz2107, Car.Plates("123", 77))
    val vaz2 = Togliatti.buildCar(Vaz2108, Car.Plates("321", 78))

    println("Экземпляры класса имеют разное внутреннее состояние:")
    vaz1.wheelToRight(10)
    println(vaz1.toString()) // Выводит 10 и случайную скорость
    vaz2.wheelToLeft(20)
    println(vaz2.toString()) // Выводит -20 и случайную скорость
}

fun innerNestedCheck() {
    val vaz = Vaz2107.build(Car.Plates("123", 77))
    val output = vaz.VazOutput() // Создаем новый объект ИЗ ЭКЗЕМПЛЯРА МАШИНЫ

    println("Скорость до проверки: ${output.getCurrentSpeed()}") // Выводит 0
    Vaz2107.test(vaz) // Газуем...
    println("Скорость после проверки: ${output.getCurrentSpeed()}") // Выводит случайную скорость
}

fun garageMake() {
    val maker = "Дядя Вася"
    val garage = object : CarFactory {
        override fun buildCar(builder: CarBuilder, plates: Car.Plates): Car {
            println("Запил Жигулей у: $maker...")
            println("Машину не проверяем... и в продакшн...")
            return builder.build(plates)
        }
    }

    val vaz = garage.buildCar(Vaz2107, Car.Plates("500", 50))
    println(vaz.toString())
}

fun getEquipment() {
    val cars = listOf(
        Vaz2107.build(Car.Plates("123", 77)),
        Vaz2108.build(Car.Plates("321", 78))
    )

    cars.forEach { car ->
        println("Оборудование: ${car.getEquipment()}")
    }
}

fun getColor() {
    val cars = listOf(
        Vaz2107.build(Car.Plates("123", 77)),
        Vaz2108.build(Car.Plates("321", 78))
    )

    cars.forEach { car ->
        println("Цвет: ${car.color}")
    }
}

fun techChecks() {
    val vaz1 = Vaz2107.build(Car.Plates("123", 77))
    val vaz2 = Vaz2108.build(Car.Plates("321", 78))

    repairEngine(vaz1)
    repairEngine(vaz2)
}

fun repairEngine(car: VazPlatform) {
    // Проверяем тип двигателя
    // В зависимости от типа двигателя выполняем разные действия
    // when обеспечивает обход всех вариантов перечисления
    when (car.engine) {
        is VazEngine.LADA_2107 -> println("Чистка карбюратора у двигателя объемом ${car.engine.volume} куб.см у машины $car")
        is VazEngine.SAMARA_2108 -> println("Угол зажигания у двигателя объемом ${car.engine.volume} куб.см у машины $car")
    }
}

class GasStation {
    var car : Car? = null
    fun refuelingCar(liters : Int) : Unit {
        try {
            if (car != null) {
                val mouth = car!!.tank.mouth
                if (mouth != null) {
                    mouth.open()
                    when (mouth) {
                        is PetrolMouth -> mouth.fuelPetrol(liters)
                        is LpgMouth -> mouth.fuelLpg(liters)
                    }
                    mouth.close()
                }
            }
        } catch(e : Error) {
            println("Ошибка: ${e.message}")
        }
    }
}
