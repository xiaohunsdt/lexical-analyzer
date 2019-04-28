package net.novaborn.fa.handler;

import net.novaborn.fa.entity.DFA;
import net.novaborn.fa.entity.NFA;
import net.novaborn.fa.entity.TransitionTable;
import org.junit.Test;

public class LexicalAnalysisHandlerTest {

    @Test
    public void handle() {
        ExpToNfaHandler expToNfaHandler = new ExpToNfaHandler();
        NFA nfa = (NFA) expToNfaHandler.handle().getResult();

        NfaToDfaHandler nfaToDfaHandler = new NfaToDfaHandler(nfa);
        DFA dfa = (DFA) nfaToDfaHandler.handle().getResult();

        DfaToTransitionTableHandler dfaToTransitionTableHandler = new DfaToTransitionTableHandler(dfa);
        TransitionTable transitionTable = (TransitionTable)dfaToTransitionTableHandler.handle().getResult();

        LexicalAnalysisHandler lexicalAnalysisHandler = new LexicalAnalysisHandler();
        System.out.println(lexicalAnalysisHandler.setOriginStr("int abc;").handle().getResult());
    }
}
