package net.novaborn.fa.hander;


import net.novaborn.fa.entity.NFA;
import org.junit.Test;

public class NfaToDfaHandlerTest {

    @Test
    public void handle() {
        ExpToNfaHandler expToNfaHandler = new ExpToNfaHandler();
        NFA nfa = (NFA) expToNfaHandler.setRegExpression("a(b|c)*").handle().getResult();

        NfaToDfaHandler nfaToDfaHandler = new NfaToDfaHandler(nfa);
        System.out.println(nfaToDfaHandler.handle().getResult());
    }
}
