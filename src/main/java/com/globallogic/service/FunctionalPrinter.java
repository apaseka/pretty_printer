package com.globallogic.service;

import com.globallogic.model.ReportLineModel;
import com.globallogic.model.ReportSpliterator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FunctionalPrinter implements PrinterInterface {

    @Override
    public void printPretty(String filePath) {
        Path path = Paths.get(filePath);

        try (Stream<String> lines = Files.lines(path)) {

            final Spliterator<String> lineSpliterator = lines.spliterator();
            final Spliterator<ReportLineModel> reportSpliterator = new ReportSpliterator(lineSpliterator);


            final Stream<ReportLineModel> report = StreamSupport.stream(reportSpliterator, false);

            final List<ReportLineModel> list = report.collect(Collectors.toList());

            printHeader();
            list.forEach(System.out::println);
            printFooter();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printFooter() {
        for (int i = 0; i < ReportSpliterator.totalPlusNumberOfColumns; i++) {
            System.out.print("-");
        }
    }

    private void printHeader() {
        printFooter();
        System.out.println();
        new PrintTableNamesService().printTableNames();
        printFooter();
        System.out.println();
    }
}
