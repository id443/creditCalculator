package org.productStar;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCalculatorWithDiscount implements IDiscount {
    private double principal;
    private double annualInterestRate;
    private double discount;
    private int years;
    private List<Payment> payments;


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    @Override
    public void setPrincipal(double principal) { this.principal = principal - discount; }

    @Override
    public void setAnnualInterestRate(double annualInterestRate) { this.annualInterestRate = annualInterestRate; }

    @Override
    public void setYears(int years) { this.years = years; }

    @Override
    public void calculatePayments() {
        double monthlyRate = annualInterestRate / 12 / 100;
        double monthlyPayment = principal * (monthlyRate * Math.pow(1 + monthlyRate, years * 12)) / (Math.pow(1 + monthlyRate, years * 12) - 1);
        payments = new ArrayList<>();

        for (int month = 1; month <= years * 12; month++) {
            double interestPayment = principal * monthlyRate;
            double principalPayment = monthlyPayment - interestPayment;
            principal -= principalPayment;

            payments.add(new Payment(month, principalPayment, interestPayment));
        }
    }

    @Override
    public double getTotalPayment() {
        return payments.stream().mapToDouble(Payment::getTotalPayment).sum();
    }

    @Override
    public double getTotalInterest() {return  payments.stream().mapToDouble(Payment::getInterestPayment).sum(); }

    @Override
    public List<Payment> getPaymentsSchedule() {
        return payments;
    }
}
