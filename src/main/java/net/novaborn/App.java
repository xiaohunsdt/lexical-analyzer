package net.novaborn;

import net.novaborn.entity.Token;
import net.novaborn.fa.handler.LexicalAnalysisHandler;

import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-10
 * Time: 10:11
 * Description:
 */
public class App {
    public static void main(String[] args) throws IOException {
        String fileName;
        if(args.length > 1){
            throw new IllegalArgumentException();
        }else if(args.length == 1){
            fileName = args[0];
        }else {
            fileName = "C:\\Users\\Administrator.C3CNPVINO5W5F1W\\Desktop\\lexical.c";
        }

        LexicalAnalysisHandler lexicalAnalysisHandler = new LexicalAnalysisHandler();

        String tempString;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        while ((tempString = reader.readLine()) != null) {
            lexicalAnalysisHandler.setOriginStr(tempString).handle();
        }

        List<Token> tokens = (List<Token>)lexicalAnalysisHandler.getResult();
        tokens.forEach(System.out::println);
    }
}
