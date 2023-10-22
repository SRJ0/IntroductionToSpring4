package aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.Signature
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import java.util.*

//XML에 <aop:aspectj-autoproxy /> 태그 추가
//@Configuration 클래스에 @EnableAspectJAutoProxy 어노테이션 적용
@Aspect
class ExecTimeAspect2 {

    @Pointcut("execution(public * aop..*(..))")
    //execution 명시자는 advice를 적용할 메서드를 지정할 때 사용
    // 수식어 public(스프링AOP는 public only) / 리턴타입 *(모든값) / 패키지 클래스 이름 메서드 이름 aop 패키지 및 하위 패키지 / (파라미터 ..(0개 이상))
    // public void set*(..) 이름이 set으로 시작하고 파라미터가 0개 이상인 메서드 호출
    // package.*.*() package 패키지의 타입에 속한 파라미터가 없는 모든 메서드 호출

    private fun publicTarget() {
    }

    //Around advice의 pointcut 설정으로 publictTarget()에 정의한 값을 사용
    //pointcut에 적용할 공통 기능으로 @Around 적용한 measure()를 사용
    @Around("publicTarget()")
    @Throws(Throwable::class)
    fun measure(joinPoint: ProceedingJoinPoint): Any {
        var start: Long = System.nanoTime()
        try {
            val result: Any = joinPoint.proceed()
            return result
        } finally {
            var finish = System.nanoTime()
            var sig: Signature = joinPoint.getSignature() // 호출 메서드
            println("%s.%s(%s) 실행 시간: %d ns\n".format(joinPoint.getTarget().javaClass.getSimpleName(), // 대상 객체
                sig.getName(), Arrays.toString(joinPoint.getArgs()), (finish - start)))

            // joinPoint.getArgs() 파라미터 목록
            // [Ljava.lang.Object;@1ebea008 ???
            // joinPoint.toLongString() 메서드 리턴 타입, 파라미터 타입 모두 표시
            // ex) execution(public abstract long aop.Calculator.factorial(long))
            // joinPoint.toShortString() 메서드 축약 표현
            // ex) execution(Calculator.factorial(..))

        }
    }

}
