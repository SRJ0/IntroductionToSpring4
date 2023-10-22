package aspect

import org.aspectj.lang.ProceedingJoinPoint

class CacheAspect {
    private val cache: Map<Long, Any> = HashMap()

    @Throws(Throwable::class)
    fun execute(joinPoint: ProceedingJoinPoint) {
        var num: Long = (Long)joinPoint.getArgs()[0]
        if (cache.containsKey(num)) {
            println("cacheaspect %d\n".format(num))
            return cache.get(num)
        }
    }


}