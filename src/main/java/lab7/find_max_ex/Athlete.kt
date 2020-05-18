package lab7.find_max_ex

class Athlete(private var _firstName: String = "", private var _lastName: String = "", private var _patronymic: String = "", private var _weight: Double = Double.NaN, private var _growth: Double = Double.NaN) {
    val firstName: String
        get() {
            return _firstName
        }

    val lastName: String
        get() {
            return _lastName
        }

    val patronymic: String
        get() {
            return _patronymic
        }

    val weight: Double
        get() {
            return _weight
        }

    val growth: Double
        get() {
            return _growth
        }
}