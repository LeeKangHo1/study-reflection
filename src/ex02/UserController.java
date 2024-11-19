package ex02;

/**
 * 2차 개발자가 작성하는 코드
 */

public class UserController {

    // 이름이 value면 생략 가능
    @RequestMapping("/login")
    public void login() {
        System.out.println("로그인");
    }

    @RequestMapping("/join")
    public void join() {
        System.out.println("회원가입");
    }

    @RequestMapping(value = "/logout")
    public void logout() {
        System.out.println("로그아웃");
    }

    // path는 클라이언트에게 설명서로 제공
    @RequestMapping("/userinfo")
    public void userinf() {
        System.out.println("유저정보");
    }
}
