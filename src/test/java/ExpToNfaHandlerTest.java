import net.novaborn.fa.hander.ExpToNfaHandler;
import org.junit.Before;
import org.junit.Test;

public class ExpToNfaHandlerTest {
    ExpToNfaHandler expToNfaHandler;

    @Before
    public void before() {
        expToNfaHandler = new ExpToNfaHandler("abc*");
    }

    @Test
    public void handle() {
        expToNfaHandler.handle().getResult();
    }
}
