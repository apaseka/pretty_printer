package com.globallogic.model;

import java.math.BigDecimal;

import static com.globallogic.model.ReportSpliterator.idMaxLength;

public class ReportLineModel {

    public static final String START = "| ";
    public static final String MIDDLE = " | ";
    public static final String END = " |";
    private Long id;
    private String type;
    private String description;
    private Boolean approved;
    private BigDecimal amount;
    private String currency;
    private String other;

    public ReportLineModel(Long id, String type, String description, Boolean approved, BigDecimal amount, String currency, String other) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.approved = approved;
        this.amount = amount;
        this.currency = currency;
        this.other = other;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(START)
                .append(id);
        appendSpaces(stringBuilder, "id").append(MIDDLE)
                .append(type);
        appendSpaces(stringBuilder, "type").append(MIDDLE)
                .append(description);
        appendSpaces(stringBuilder, "desc").append(MIDDLE)
                .append(approved);
        appendSpaces(stringBuilder, "appr").append(MIDDLE);
        appendSpaces(stringBuilder, "amount").append(amount).append(MIDDLE)
                .append(currency);
        appendSpaces(stringBuilder, "currency").append(MIDDLE)
                .append(other);
        appendSpaces(stringBuilder, "other").append(END);

        return stringBuilder.toString();
    }

    private StringBuilder appendSpaces(StringBuilder stringBuilder, String fieldName) {
        switch (fieldName) {
            case "id":
                for (int i = 0; i < idMaxLength - String.valueOf(id).length(); i++) {
                    stringBuilder.append(" ");
                }
                break;
            case "type":
                for (int i = 0; i < ReportSpliterator.typeMaxLength - type.length(); i++) {
                    stringBuilder.append(" ");
                }
                break;

            case "desc":
                for (int i = 0; i < ReportSpliterator.descMaxLength - description.length(); i++) {
                    stringBuilder.append(" ");
                }
                break;

            case "appr":
                for (int i = 0; i < ReportSpliterator.apprMaxLength - Boolean.toString(approved).length(); i++) {
                    stringBuilder.append(" ");
                }
                break;

            case "amount":
                for (int i = 0; i < ReportSpliterator.amountMaxLength - String.valueOf(amount).length(); i++) {
                    stringBuilder.append(" ");
                }
                break;
            case "currency":
                for (int i = 0; i < ReportSpliterator.currencyMaxLength - currency.length(); i++) {
                    stringBuilder.append(" ");
                }
                break;
            case "other":
                for (int i = 0; i < ReportSpliterator.otherMaxLength - other.length(); i++) {
                    stringBuilder.append(" ");
                }
                break;
        }
        return stringBuilder;
    }

}



