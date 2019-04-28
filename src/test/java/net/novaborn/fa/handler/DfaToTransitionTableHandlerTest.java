package net.novaborn.fa.handler;

import net.novaborn.fa.entity.DFA;
import net.novaborn.fa.entity.NFA;
import org.junit.Test;

public class DfaToTransitionTableHandlerTest {

    @Test
    public void handle() {
        ExpToNfaHandler expToNfaHandler = new ExpToNfaHandler();
        NFA nfa = (NFA) expToNfaHandler.handle().getResult();

        NfaToDfaHandler nfaToDfaHandler = new NfaToDfaHandler(nfa);
        DFA dfa = (DFA) nfaToDfaHandler.handle().getResult();

        DfaToTransitionTableHandler dfaToTransitionTableHandler = new DfaToTransitionTableHandler(dfa);
        System.out.println(dfaToTransitionTableHandler.handle().getResult());
    }
}
