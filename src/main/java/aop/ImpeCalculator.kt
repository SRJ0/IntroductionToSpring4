package aop

class ImpeCalculator: Calculator {
    override fun factorial(num: Long): Long {
        val start = System.nanoTime()
        var result: Long = 1
        for(i in 1..num) {
            result *= i
        }
        val end = System.nanoTime()
        println("Imperative : " + (end - start))
        return result
    }
}