package lab7.find_max_ex

import common.FileManager
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    val inputFile = FileManager.getFileByPath(args[0])
    val reader = Scanner(inputFile)
    val athletes: ArrayList<Athlete> = ArrayList()
    reader.use {
        while (reader.hasNext()) {
            athletes.add(Athlete(reader.next(), reader.next(), reader.next(), reader.nextDouble(), reader.nextDouble()))
        }
    }

    val athleteMaxWeight: Athlete? = findMaxEx(athletes, ::maxWeight)
    if (athleteMaxWeight != null) printAthleteInfo(athleteMaxWeight)

    println()

    val athleteMaxGrowth: Athlete? = findMaxEx(athletes, ::maxGrowth)
    if (athleteMaxGrowth != null) printAthleteInfo(athleteMaxGrowth)
}

fun maxWeight(la: Athlete, ra: Athlete): Boolean {
    return la.weight >= ra.weight
}

fun maxGrowth(la: Athlete, ra: Athlete): Boolean {
    return la.growth >= ra.growth
}

fun printAthleteInfo(athlete: Athlete) {
    println("FirstName: " + athlete.firstName + "\n" +
            "LastName: " + athlete.lastName + "\n" +
            "Patronymic: " + athlete.patronymic + "\n" +
            "Weight: " + athlete.weight + "\n" +
            "Growth: " + athlete.growth
    )
}