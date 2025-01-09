package org.productStar;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите сумму долга:");
        double principal = scanner.nextDouble();

        System.out.println("Введите срок кредита в годах:");
        int years = scanner.nextInt();

        System.out.println("Введите процентную ставку:");
        double annualInterestRate = scanner.nextDouble();

        System.out.println("Выберите вид платежа (1 - аннуитетный, 2 - дифференцированный):");
        int paymentType = scanner.nextInt();

        ICalculator calculator;

        if (paymentType == 1) {
                calculator = new AnnuityCalculator();
        } else {
                calculator = new DifferentiatedCalculator();
        }

        calculator.setPrincipal(principal);
        calculator.setAnnualInterestRate(annualInterestRate);
        calculator.setYears(years);

        calculator.calculatePayments();

        printSchedule(calculator);
    }

    private static void printSchedule(ICalculator calculator) {
        System.out.println("График платежей:");
        for (Payment payment : calculator.getPaymentsSchedule()) {
            System.out.printf("Месяц: %d, Платеж по основному долгу: %.2f, Процентный платеж: %.2f, Общий платеж: %.2f%n",
                    payment.getMonth(), payment.getPrincipalPayment(), payment.getInterestPayment(), payment.getTotalPayment());
        }

        System.out.printf("Общая сумма выплат: %.2f%n", calculator.getTotalPayment());
        System.out.printf("Общая сумма процентов: %.2f%n", calculator.getTotalInterest());
    }
}