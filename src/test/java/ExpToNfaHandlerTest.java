import net.novaborn.fa.hander.ExpToNfaHandler;
import org.junit.Before;
import org.junit.Test;

public class ExpToNfaHandlerTest {
    ExpToNfaHandler expToNfaHandler;

    @Before
    public void before() {
        expToNfaHandler = new ExpToNfaHandler();
    }

    @Test
    public void handle() {
        System.out.println(expToNfaHandler.setRegExpression("abcaada").handle().getResult());
    }
}
