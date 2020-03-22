package com.globallogic.model;

import java.math.BigDecimal;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ReportSpliterator implements Spliterator<ReportLineModel> {

    public static int idMaxLength = 2;
    public static int typeMaxLength = 4;
    public static int descMaxLength = 12;
    public static int apprMaxLength = 10;
    public static int amountMaxLength = 6;
    public static int currencyMaxLength = 8;
    public static int otherMaxLength = 8;
    public static int totalPlusNumberOfColumns = 0;
    private final Spliterator<String> lineSpliterator;
    private Long id;
    private String type;
    private String description;
    private Boolean approved;
    private BigDecimal amount;
    private String currency;
    private String other;

    public ReportSpliterator(Spliterator<String> lineSpliterator) {
        this.lineSpliterator = lineSpliterator;

    }

    @Override
    public boolean tryAdvance(Consumer<? super ReportLineModel> consumer) {
        if (this.lineSpliterator.tryAdvance(line -> {
            final String[] split = line.split(";");

            int i = 0, j = 0, k;

            try {
                this.id = Long.parseLong(split[i++]);

                if ((k = (split[j++]).length()) > idMaxLength)
                    if (k % 2 == 0) {
                        idMaxLength = k;
                    } else {
                        idMaxLength = k + 1;
                    }

                this.type = (split[i++]);
                if ((k = (split[j++]).length()) > typeMaxLength)
                    if (k % 2 == 0) {
                        typeMaxLength = k;
                    } else {
                        typeMaxLength = k + 1;
                    }

                this.description = (split[i++]);
                if ((k = (split[j++]).length()) > descMaxLength)
                    if (k % 2 == 0) {
                        descMaxLength = k;
                    } else {
                        descMaxLength = k + 1;
                    }

                this.approved = Boolean.getBoolean(split[i++]);
                if ((k = (split[j++]).length()) > apprMaxLength)
                    if (k % 2 == 0) {
                        apprMaxLength = k;
                    } else {
                        apprMaxLength = k + 1;
                    }

                this.amount = BigDecimal.valueOf(Double.parseDouble((split[i++]).replace(",", ".")));
                if ((k = (split[j++]).length()) > amountMaxLength)
                    if (k % 2 == 0) {
                        amountMaxLength = k;
                    } else {
                        amountMaxLength = k + 1;
                    }

                this.currency = (split[i++]);
                if ((k = (split[j++]).length()) > currencyMaxLength)
                    if (k % 2 == 0) {
                        currencyMaxLength = k;
                    } else {
                        currencyMaxLength = k + 1;
                    }

                this.other = (split[i]);
                if ((k = (split[j]).length()) > otherMaxLength)
                    if (k % 2 == 0) {
                        otherMaxLength = k;
                    } else {
                        otherMaxLength = k + 1;
                    }
            } catch (NumberFormatException e) {
                tryAdvance(consumer);
            }
        })) {
            final ReportLineModel reportLineModel = new ReportLineModel(id, type, description, approved, amount, currency, other);
            consumer.accept(reportLineModel);
            totalPlusNumberOfColumns = 7 * 3 + 1 + idMaxLength + typeMaxLength + descMaxLength + apprMaxLength + amountMaxLength + currencyMaxLength + otherMaxLength;

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Spliterator<ReportLineModel> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return lineSpliterator.estimateSize();
    }

    @Override
    public int characteristics() {
        return lineSpliterator.characteristics();
    }
}
