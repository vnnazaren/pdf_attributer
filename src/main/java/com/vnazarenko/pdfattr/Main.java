package com.vnazarenko.pdfattr;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manager manager = new Manager();

        while (true) {
            PrintMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Вводим путь к каталогу в котором будет обработка:");
                    manager.checkFiles(scanner.next());
                    break;
                case 2:
                    System.out.println("Вводим путь к каталогу в котором будет обработка:");
                    manager.startProcess(scanner.next());
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Выберите один из пунктов меню.");
            }
            System.out.println();
        }
    }

    public static void PrintMenu() {
        System.out.println("1 - Проверяем корректность двойных тире.");
        System.out.println("2 - Обрабатываем файлы.");
        System.out.println("0 - Выходим из программы.");
    }
}