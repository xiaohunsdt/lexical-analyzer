package net.novaborn;

import net.novaborn.entity.Token;
import net.novaborn.fa.entity.DFA;
import net.novaborn.fa.entity.NFA;
import net.novaborn.fa.entity.TransitionTable;
import net.novaborn.fa.hander.DfaToTransitionTableHandler;
import net.novaborn.fa.hander.ExpToNfaHandler;
import net.novaborn.fa.hander.LexicalAnalysisHandler;
import net.novaborn.fa.hander.NfaToDfaHandler;

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
        if(args.length > 1){
            throw new IllegalArgumentException();
        }

        ExpToNfaHandler expToNfaHandler = new ExpToNfaHandler();
        NFA nfa = (NFA) expToNfaHandler.handle().getResult();

        NfaToDfaHandler nfaToDfaHandler = new NfaToDfaHandler(nfa);
        DFA dfa = (DFA) nfaToDfaHandler.handle().getResult();

        DfaToTransitionTableHandler dfaToTransitionTableHandler = new DfaToTransitionTableHandler(dfa);
        TransitionTable transitionTable = (TransitionTable)dfaToTransitionTableHandler.handle().getResult();

        LexicalAnalysisHandler lexicalAnalysisHandler = new LexicalAnalysisHandler(transitionTable);

        String tempString;
        BufferedReader reader = new BufferedReader(new FileReader(new File(args[0])));
        while ((tempString = reader.readLine()) != null) {
            lexicalAnalysisHandler.setOriginStr(tempString).handle();

        }

        List<Token> tokens = (List<Token>)lexicalAnalysisHandler.getResult();
        tokens.forEach(System.out::println);
    }
}
