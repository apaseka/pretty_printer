package com.globallogic;

import com.globallogic.service.FunctionalPrinter;
import com.globallogic.service.PrinterInterface;

public class Main {
    public static void main(String[] args) {
        final PrinterInterface functionalPrinter = new FunctionalPrinter();
        functionalPrinter.printPretty("report2017.csv");
    }
}
