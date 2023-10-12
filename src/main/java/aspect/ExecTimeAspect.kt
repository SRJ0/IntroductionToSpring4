package aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.Signature
import java.util.*

class ExecTimeAspect {
    @Throws(Throwable::class)
    fun measure(joinPoint: ProceedingJoinPoint): Any {
        var start: Long = System.nanoTime()
        try {
            var result: Any = joinPoint.proceed()
            return result
        } finally {
            var finish: Long = System.nanoTime()
            var sig: Signature = joinPoint.getSignature()
            println(joinPoint.getTarget().javaClass.getSimpleName() + "|" + sig.getName() + "|" + Arrays.toString(joinPoint.getArgs()) + "|" + (finish - start))
        }
    }
}