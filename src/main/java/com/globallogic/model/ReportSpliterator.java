package com.globallogic.model;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

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

    IntPredicate evenNumber = x -> x % 2 == 0;

    public ReportSpliterator(Spliterator<String> lineSpliterator) {
        this.lineSpliterator = lineSpliterator;

    }

    @Override
    public boolean tryAdvance(Consumer<? super ReportLineModel> consumer) {
        if (this.lineSpliterator.tryAdvance(line -> {
            final String[] split = line.split(";");

            int i = 0, k = 0;

            try {
                this.id = Long.parseLong(split[i++]);
                idMaxLength = Math.max(idMaxLength, (split[k++]).length());

                this.type = (split[i++]);
                typeMaxLength = Math.max(typeMaxLength, (split[k++]).length());

                this.description = (split[i++]);
                descMaxLength = Math.max(descMaxLength, (split[k++]).length());

                this.approved = Boolean.getBoolean(split[i++]);
                apprMaxLength = Math.max(apprMaxLength, (split[k++]).length());

                this.amount = BigDecimal.valueOf(Double.parseDouble((split[i++]).replace(",", ".")));
                amountMaxLength = Math.max(amountMaxLength, (split[k++]).length());

                this.currency = (split[i++]);
                currencyMaxLength = Math.max(currencyMaxLength, (split[k++]).length());

                this.other = (split[i]);
                otherMaxLength = Math.max(otherMaxLength, (split[k]).length());

            } catch (NumberFormatException e) {
                tryAdvance(consumer);
            }
        })) {

            final ReportLineModel reportLineModel = new ReportLineModel(id, type, description, approved, amount, currency, other);
            consumer.accept(reportLineModel);

            makeColumnMaxLengthEven();

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

    private void makeColumnMaxLengthEven() {
        final Field[] declaredFields = ReportSpliterator.class.getDeclaredFields();
        Stream.of(declaredFields).filter(field -> field.getName().contains("Max")).forEach(declaredField -> {
            try {
                final int fieldLength = declaredField.getInt(null);
                if (!evenNumber.test(fieldLength)) declaredField.setInt(null, fieldLength + 1);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
