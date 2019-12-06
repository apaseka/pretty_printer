package com.globallogic.service;

import java.io.FileNotFoundException;

public interface PrinterInterface {

    void printPretty(String filePath) throws FileNotFoundException;
}
