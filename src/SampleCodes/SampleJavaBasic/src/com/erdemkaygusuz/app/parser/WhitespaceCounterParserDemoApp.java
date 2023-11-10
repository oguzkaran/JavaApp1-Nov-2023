package com.erdemkaygusuz.app.parser;

import com.alidenizesen.parser.WhitespaceCounterParser;
import com.egekucuk.parser.source.StringSource;

import java.util.Scanner;

public class WhitespaceCounterParserDemoApp {
    public static void run()
    {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print("Bir yazı giriniz:");
            String str = kb.nextLine();

            if ("elma".equals(str))
                break;

            StringSource ss = new StringSource(str);
            WhitespaceCounterParser parser = new WhitespaceCounterParser(ss);

            parser.parse();

            System.out.printf("Number of whitespaces:%d%n", parser.getCount());
        }
    }

    public static void main(String[] args)
    {
        run();
    }
}
