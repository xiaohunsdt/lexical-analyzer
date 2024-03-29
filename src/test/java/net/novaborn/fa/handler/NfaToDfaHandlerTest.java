package net.novaborn.fa.handler;


import net.novaborn.fa.entity.NFA;
import org.junit.Test;

public class NfaToDfaHandlerTest {

    @Test
    public void handle() {
        ExpToNfaHandler expToNfaHandler = new ExpToNfaHandler();
        NFA nfa = (NFA) expToNfaHandler.handle().getResult();

        NfaToDfaHandler nfaToDfaHandler = new NfaToDfaHandler(nfa);
        System.out.println(nfaToDfaHandler.handle().getResult());
    }
}
