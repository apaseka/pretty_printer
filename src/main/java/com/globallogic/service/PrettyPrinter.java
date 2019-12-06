package com.globallogic.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PrettyPrinter implements PrinterInterface {

    public void printPretty(String filePath) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filePath));
        Map<Integer, Integer> widhtSizes = new HashMap<>();

        //divided csv by row
        scanner.useDelimiter("\r\n");

        int columnsNumber = 0;
        List<String> reportElements = new ArrayList<>();

        while (scanner.hasNext()) {
            int index = 0;

            //divide row into columns
            String[] split = scanner.next().split(";");

            columnsNumber = split.length;

            for (String s : split) {
                //filling map with maximum String length
                if (widhtSizes.getOrDefault(index, 0) < s.length()) {
                    widhtSizes.put(index, s.length() + 1);
                }
                index = ++index;
                //adding all cells to List
                reportElements.add("|" + s);
            }
        }
        //getting total width of report for splitter printing
        int totalLength = widhtSizes.values().stream().mapToInt(Integer::intValue).sum() + columnsNumber;

        printSpecial(totalLength, '-');
        int position = 0;
        boolean isHeader = true;

        //printing report
        for (String reportElement : reportElements) {
            if (position % columnsNumber == 0) {
                System.out.println();
                if (isHeader && (position + 1) % (columnsNumber + 1) == 0) {
                    printSpecial(totalLength, '-');
                    System.out.println();
                    isHeader = false;
                }
                position = 0;
            }
            System.out.print(reportElement);
            printSpecial((widhtSizes.get(position) - reportElement.length()), ' ');
            position++;
            if (position % columnsNumber == 0) {
                System.out.print('|');
            }
        }
        System.out.println();
        printSpecial(totalLength, '-');

        scanner.close();
    }

    private void printSpecial(int totalLength, char sign) {
        for (int i = 0; i <= totalLength; i++) {
            System.out.print(sign);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrettyPrinter prettyPrinter = new PrettyPrinter();
        prettyPrinter.printPretty("C:\\Study\\pretty_printer\\src\\main\\resources\\report2017.csv");
    }
}
