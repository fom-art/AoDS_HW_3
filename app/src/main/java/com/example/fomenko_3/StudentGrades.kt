package com.example.fomenko_3

class StudentGrades(private var gradeslist: MutableList<Int>) {

    private var isChanged = false
    private var maxGrade = getMaxGrade()
    private var minGrade = getMinGrade()
    private var averageGrade = getAverage()
    private var lessThanAverage = getLessThanAverage()
    private var greaterThanAverage = getGreaterThanAverage()
    private var excellentAmount = getExcellentAmount()
    private var goodAmount = getGoodAmount()
    private var satisfactorilyAmount = getSatisfactorilyAmount()

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

    fun getGrades(): MutableList<Int> {
        return gradeslist
    }

    fun setGrades(newGradesList: MutableList<Int>) {
        gradeslist = newGradesList
        isChanged = true
        updateData()
    }

    fun addGrades(grade: Int) {
        gradeslist.add(grade)
        isChanged = true
        updateData()
    }

    fun getMaxGrade(): Int {
        if (!isChanged){
            return maxGrade
        }
        return gradeslist.maxOrNull() ?: -1
    }

    fun getMinGrade(): Int {
        if (!isChanged){
            return minGrade
        }
        return gradeslist.minOrNull() ?: -1
    }

    fun getAverage(): Double {
        if (!isChanged){
            return averageGrade
        }
        return gradeslist.average()
    }

    fun getLessThanAverage(): MutableList<Int> {
        if (!isChanged){
            return lessThanAverage
        }
        val resultList: MutableList<Int> = mutableListOf()
        for (grade in gradeslist) {
            if (grade.toDouble() < averageGrade) {
                resultList.add(grade)
            }
        }
        if (resultList.isEmpty()) {
            return mutableListOf(-1)
        }
        return resultList
    }

    fun getGreaterThanAverage(): MutableList<Int> {
        if (!isChanged){
            return greaterThanAverage
        }
        val resultList: MutableList<Int> = mutableListOf()
        for (grade in gradeslist) {
            if (grade > averageGrade) {
                resultList.add(grade)
            }
        }
        if (resultList.isEmpty()) {
            return mutableListOf(-1)
        }
        return resultList
    }

    fun getExcellentAmount(): Int {
        if (!isChanged){
            return excellentAmount
        }
        var count = 0
        for (grade in gradeslist) {
            if (grade >= EXCELLENT_THRESHOLD) {
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
            if (grade >= GOOD_THRESHOLD) {
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
            if (grade >= SATISFACTORILY_THRESHOLD) {
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