package aop

import org.aspectj.lang.ProceedingJoinPoint

internal class temp {
    @Throws(Throwable::class)
    fun measure(joinPoint: ProceedingJoinPoint): Any {
        return joinPoint.proceed()
    }
}
