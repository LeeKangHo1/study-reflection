package ex02;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Router router = new Router(new UserController());

        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();

        router.routing(path);
    }
}
