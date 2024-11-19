package ex03;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {

    public static Set<Object> componentScan(String pkg) throws Exception {
        System.out.println("-------------------- 컴포넌트 스캔 start");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Set<Object> instances = new HashSet<>();

        URL packageUrl = classLoader.getResource(pkg);
        // os?툴?은 파일과 폴더 구분하지 않기 때문에 packageDirectory는 폴더다
        File packageDirectory = new File(packageUrl.toURI());
        for (File file : packageDirectory.listFiles()) {
            // 컴퓨터가 보는 파일은 .class -> 툴은 .java만들면 자동으로 .class만들어줌
            if (file.getName().endsWith(".class")) {
                String className = pkg + "." + file.getName().replace(".class", "");
                System.out.println(className);
                Class cls = Class.forName(className);
                if (cls.isAnnotationPresent(Controller.class)) {
                    System.out.println("스캔완료 : "+cls);
                    Object instance = cls.newInstance();
                    instances.add(instance);
                }
            }
        }
        System.out.println("-------------------- 컴포넌트 스캔 end");
        // Set<Object> instances -> srping의 IOC 컨테이너
        return instances;
    }

    public static void findUri(Set<Object> instances, String uri) throws Exception {
        boolean isFind = false;
        for (Object instance : instances) {
            Class cls = instance.getClass();

            Method[] methods = cls.getDeclaredMethods();

            for (Method mt : methods) {
                Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class);
                RequestMapping rm = (RequestMapping) anno;
                if (rm.uri().equals(uri)) {
                    isFind = true;
                    mt.invoke(instance);
                }
            }

        }
        if (isFind == false) {
            System.out.println("404 Not Found");
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String uri = sc.nextLine();

        Set<Object> instances = componentScan("ex03");
        findUri(instances, uri);

    }

}
