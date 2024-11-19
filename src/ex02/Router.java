package ex02;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 1차 개발자가 작성하는 코드
 */

public class Router {

    UserController uc;

    public Router(UserController uc) {
        this.uc = uc;
    }

    public void routing(String path) {
        // import java.lang.reflect.Method;
        // uc안의 모든 메서드(public)를 메서드 배열 methods에 넣는다.
        // 1. 메서드 찾아내기
        Method[] methods = uc.getClass().getMethods();
//        System.out.println(methods[0].getName());
        // invoke: 호출 -> 메서드 이름 안 적어도 호출 가능
//        methods[0].invoke(uc);

        // 2. 어노테이션 체크하기
        for (Method m : methods) {
            // 어노테이션이 없으면 rm은 null
            RequestMapping rm = m.getAnnotation(RequestMapping.class);

            // 3. value와 path 일치 확인해서 일치하면 invoke 하기
            if (rm == null) break; // null이면 밑에 코드 무시하고 다시 for문으로
            // 이 문장이 필요한 이유 -> Object를 상속 중이라 내가 적은 메서드는
            // 3개지만 실제로는 12개의 메소드가 있다.
            if (rm.value().equals(path)) {
                try {
                    m.invoke(uc); // 매개변수에 uc 반드시 넣어줘야 한다
                    // 메서드를 어느 객체의 인스턴스에서 호출할지를 지정

                } catch (Exception e) {
                    throw new RuntimeException("메서드 실행중 오류가 발생했어요");
                }
            }
        }
    }
}
