package aop


// 기존 소스 ImpeCalculator RecuCalculator 를 수정하지 않고 실행 시간 출력
// 실행 시간 구하는 코드 중복을 제거, 쉽게 변경 가능

class ExecTimeCalculator(private val delegate: Calculator) : Calculator {
    override fun factorial(num: Long): Long {
        val start: Long = System.nanoTime()
        val result: Long = delegate.factorial(num)
        // 핵심 기능을 직접 구현하지 않고 다른 객체(대상 객체)에 위임하는 객체를 프록시라고 한다.
        // 사실 기능을 추가하는 점에서 데코레이터에 가깝다.
        // 프록시는 여러 객체에 공통으로 적용될 수 있는 기능(Aspect)을 구현한다.
        val end: Long = System.nanoTime()
        println(delegate.javaClass.getSimpleName() + " : " + (end - start))
        return result
    }

}
// 이렇게 공통 기능과 핵심 기능 구현을 분리하여 재사용성을 높이는 것이 AOP이다. Aspect Oriented Programming
// 핵심 기능에 공통 기능을 삽입하기 위한 방법
// 1 컴파일 시점에 코드에
// 2 클래스 로딩 시점에 바이트 코드에
// AspectJ 같은 AOP 전용 도구 필요
// 3 런타임에 프록시 객체를 생성하여 -> 주된 spring AOP 작동 방식, 2 일부 지원

// Advice는 공통 기능을 적용할 시점을 정의한다
// Joinpoint는 advice를 적용할 수 있는 지점을 말한다
// Pointcut은 실제 advice가 적용되는 joinpoint로서, joinpoint의 부분 집합이다.
// Weaving advice를 핵심 로직에 적용하는 것을 말한다.

// advice 종류
// before advice 대상 객체의 메서드 호출 전
// after returning advice 메서드가 익셉션 없이 실행된 이후
// after throwing advice 메서드를 실행하는 도중 익셉션이 발생한 경우
// after advice 익셉션 관계없이 메서드 실행 후
// around advice 메서드 실행 전 또는 익셉션 발생 시점에 - 다양한 시점에 기능을 삽입할 수 있어 널리 사용

// aop 구현
// 1 공통 기능 구현 / XML 기반 POJO* 클래스 이용 or @Aspect 이용
// *Plain Old Java Object - EJB와 같이 특정 기술에 종속된 코드와 반대의 의미로 사용
// 2 공통 기능(aspect)을 어디(pointcut)에 적용할 지(advice)를 설정

