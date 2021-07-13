package com.hugo;

import com.hugo.service.RobotService;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        RobotService service = new RobotService();
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println("To quit type EXIT");

        do {
            input = sc.nextLine();
            // Output response from the adapter (e.g. REPORT)
            System.out.println(service.command(input));
        }
        while (!"exit".equalsIgnoreCase(input));

    }
}