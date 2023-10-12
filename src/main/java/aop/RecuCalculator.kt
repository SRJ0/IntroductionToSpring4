package aop

class RecuCalculator: Calculator {
    override fun factorial(num: Long): Long {
        return if (num.toInt() == 0) 1
        else num * factorial(num - 1)
    }
}