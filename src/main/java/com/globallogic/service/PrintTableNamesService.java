package com.globallogic.service;

import java.util.Stack;

import static com.globallogic.model.ReportLineModel.*;
import static com.globallogic.model.ReportSpliterator.*;

public class PrintTableNamesService {

    private static Stack<Integer> correction = new Stack<>();

    public void printTableNames() {

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(START);
        addSpaces(stringBuilder, "Id", idMaxLength).append("Id");
        addSpaces(stringBuilder, "Id", idMaxLength).append(MIDDLE);
        addSpaces(stringBuilder, "Type", typeMaxLength).append("Type");
        addSpaces(stringBuilder, "Type", typeMaxLength).append(MIDDLE);
        addSpaces(stringBuilder, "Description", descMaxLength).append("Description");
        addSpaces(stringBuilder, "Description", descMaxLength).append(MIDDLE);
        addSpaces(stringBuilder, "IsApproved", apprMaxLength).append("IsApproved");
        addSpaces(stringBuilder, "IsApproved", apprMaxLength).append(MIDDLE);
        addSpaces(stringBuilder, "Amount", amountMaxLength).append("Amount");
        addSpaces(stringBuilder, "Amount", amountMaxLength).append(MIDDLE);
        addSpaces(stringBuilder, "Currency", currencyMaxLength).append("Currency");
        addSpaces(stringBuilder, "Currency", currencyMaxLength).append(MIDDLE);
        addSpaces(stringBuilder, "Other", otherMaxLength).append("Other");
        addSpaces(stringBuilder, "Other", otherMaxLength).append(END);
        System.out.println(stringBuilder.toString());
    }

    private StringBuilder addSpaces(StringBuilder stringBuilder, String tableName, int length) {

        int tableLength = tableName.length();

        if (tableLength % 2 != 0)
            if (correction.empty()) {
                correction.push(1);
                --tableLength;
            } else {
                correction.pop();
                ++tableLength;
            }

        for (int i = 0; i < (length - tableLength) / 2; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder;
    }
}
