package ex02;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 런타임(jvm)때 분석할지 컴파일(컴파일러, javac)단계에서 분석할지 설정
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping { // RequestMapping -> 어노테이션 이름
    // 어노테이션 속성에서 key
    String value();
}
