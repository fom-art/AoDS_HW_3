package com.example.fomenko_3

class StudentGrades <T : Number>(private var gradeslist: MutableList<T>) {

    private var isChanged = true
    private var maxGrade = getMaxGrade()
    private var minGrade = getMinGrade()
    private var averageGrade = getAverage()
    private var lessThanAverage = getLessThanAverage()
    private var greaterThanAverage = getGreaterThanAverage()
    private var excellentAmount = getExcellentAmount()
    private var goodAmount = getGoodAmount()
    private var satisfactorilyAmount = getSatisfactorilyAmount()


    private lateinit var a: MutableList<T>

    operator fun get(i: Int): T {
        return a[i]
    }

    fun updateData() {
        getMaxGrade()
        getMinGrade()
        getAverage()
        getLessThanAverage()
        getGreaterThanAverage()
        getExcellentAmount()
        getGoodAmount()
        getSatisfactorilyAmount()
        isChanged = false
    }

    fun getGrades(): MutableList<T> {
        return gradeslist
    }

    fun setGrades(newGradesList: MutableList<T>) {
        var isLeagal = true
        for (grades in newGradesList) {
            if (grades.toInt() !in 0..100){
                if(grades.toDouble() > 100){
                    throw IllegalArgumentException("Grades must be in range from 0 to 100")
                    isLeagal = false
                }
            }
        }
        if (isLeagal) {
            gradeslist = newGradesList
            isChanged = true
            updateData()
        }
    }

    fun addGrades(grade: T) {
        if (grade.toInt() in 0..100) {
            if (grade.toDouble() < 100) {
                gradeslist.add(grade)
                isChanged = true
                updateData()
            } else {
                throw IllegalArgumentException("Grades must be in range from 0 to 100")
            }
        } else {
            throw IllegalArgumentException("Grades must be in range from 0 to 100")
        }
    }

    fun getMaxGrade(): T {
        if (!isChanged){
            return maxGrade
        }
        var max: T = gradeslist[0]
        for (grades in gradeslist) {
            if (grades.toDouble() > max.toDouble()){
                max = grades
            }
        }
        return max
    }

    fun getMinGrade(): T {
        if (!isChanged){
            return minGrade
        }
        var min: T = gradeslist[0]
        for (grades in gradeslist) {
            if (grades.toDouble() < min.toDouble()){
                min = grades
            }
        }
        return min
    }

    fun getAverage(): Double {
        if (!isChanged){
            return averageGrade
        }
        averageGrade = 0.0
        for (grades in gradeslist) {
            averageGrade += grades.toDouble()
        }
        averageGrade /= gradeslist.size

        return averageGrade
    }

    fun getLessThanAverage(): MutableList<T> {
        if (!isChanged){
            return lessThanAverage
        }
        val resultList: MutableList<T> = mutableListOf()
        for (grade in gradeslist) {
            if (grade.toDouble() < averageGrade) {
                resultList.add(grade)
            }
        }
        return resultList
    }

    fun getGreaterThanAverage(): MutableList<T> {
        if (!isChanged){
            return greaterThanAverage
        }
        val resultList: MutableList<T> = mutableListOf()
        for (grade in gradeslist) {
            if (grade.toDouble() > averageGrade) {
                resultList.add(grade)
            }
        }
        return resultList
    }

    fun getExcellentAmount(): Int {
        if (!isChanged){
            return excellentAmount
        }
        var count = 0
        for (grade in gradeslist) {
            if (grade.toDouble() >= EXCELLENT_THRESHOLD) {
                count++
            }
        }
        return count
    }

    fun getGoodAmount(): Int {
        if (!isChanged){
            return goodAmount
        }
        var count = 0
        for (grade in gradeslist) {
            if (grade.toDouble() >= GOOD_THRESHOLD) {
                count++
            }
        }
        return count
    }

    fun getSatisfactorilyAmount(): Int {
        if (!isChanged){
            return satisfactorilyAmount
        }
        var count = 0
        for (grade in gradeslist) {
            if (grade.toDouble() >= SATISFACTORILY_THRESHOLD) {
                count++
            }
        }
        return count
    }

    fun getGradesInString() : String {
        return gradeslist.toString().substring(1, gradeslist.toString().length - 1)
    }

    companion object {
        private const val EXCELLENT_THRESHOLD = 91
        private const val GOOD_THRESHOLD = 71
        private const val SATISFACTORILY_THRESHOLD = 60
    }
}