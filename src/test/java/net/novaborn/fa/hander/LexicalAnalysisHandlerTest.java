package net.novaborn.fa.hander;

import net.novaborn.fa.entity.DFA;
import net.novaborn.fa.entity.NFA;
import net.novaborn.fa.entity.TransitionTable;
import org.junit.Test;

public class LexicalAnalysisHandlerTest {

    @Test
    public void handle() {
        ExpToNfaHandler expToNfaHandler = new ExpToNfaHandler();
        NFA nfa = (NFA) expToNfaHandler.setRegExpression("a(b|c)*").handle().getResult();

        NfaToDfaHandler nfaToDfaHandler = new NfaToDfaHandler(nfa);
        DFA dfa = (DFA) nfaToDfaHandler.handle().getResult();

        DfaToTransitionTableHandler dfaToTransitionTableHandler = new DfaToTransitionTableHandler(dfa);
        TransitionTable transitionTable = (TransitionTable)dfaToTransitionTableHandler.handle().getResult();

        LexicalAnalysisHandler lexicalAnalysisHandler = new LexicalAnalysisHandler(transitionTable);
        System.out.println(lexicalAnalysisHandler.handle().getResult());
    }
}
