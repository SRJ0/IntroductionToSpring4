package aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
internal class ExecTimeAspect2 {
    @Pointcut("execution(public * aop..*(..))")
    private fun publicTarget() {
    }

    @Around("publicTarget()")
    fun measure(joinPoint: ProceedingJoinPoint?): Any {
        return Any()
    }
}