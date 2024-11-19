package ex01;

/**
 * 1차 개발자가 작성하는 코드
 */

public class Router {

    UserController uc;

    public Router(UserController uc) {
        this.uc = uc;
    }

    public void routing(String path) {
        if (path.equals("/login")) {
            uc.login();
        } else if (path.equals("/join")) {
            uc.join();
        } else if (path.equals("/logout")) {
            uc.logout();
        }
    }
}
