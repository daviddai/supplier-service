package com.micro.services.supplier.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.micro.services.supplier.svc",
                "com.micro.services.event.bus"
        }
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logo();
    }

    private static void logo() {
        System.out.println(" ____  _     ____  ____  _     _  _____ ____    ____  _____ ____  _     _  ____  _____");
        System.out.println("/ ___\\/ \\ /\\/  __\\/  __\\/ \\   / \\/  __//  __\\  / ___\\/  __//  __\\/ \\ |\\/ \\/   _\\/  __/");
        System.out.println("|    \\| | |||  \\/||  \\/|| |   | ||  \\  |  \\/|  |    \\|  \\  |  \\/|| | //| ||  /  |  \\  ");
        System.out.println("\\___ || \\_/||  __/|  __/| |_/\\| ||  /_ |    /  \\___ ||  /_ |    /| \\// | ||  \\_ |  /_ ");
        System.out.println("\\____/\\____/\\_/   \\_/   \\____/\\_/\\____\\\\_/\\_\\  \\____/\\____\\\\_/\\_\\\\__/  \\_/\\____/\\____\\");
    }

}
