package net.novaborn.fa.hander;

import net.novaborn.entity.Token;
import net.novaborn.fa.entity.NFA;
import net.novaborn.fa.entity.TransitionTable;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-27
 * Time: 13:34
 * Description:
 */
public class LexicalAnalysisHandler implements BaseHandler {
    private TransitionTable transitionTable;
    private String originStr;
    private List<Token> tokens = new ArrayList<>();

    public LexicalAnalysisHandler(TransitionTable transitionTable) {
        this.transitionTable = transitionTable;
    }

    @Override
    public BaseHandler handle() {
        //init state id is 0


        //Longest matching algorithm by stack
        Stack<Integer> stack = new Stack<>();
        CharBuffer byteBuffer = CharBuffer.allocate(originStr.length()).put(originStr);
        byteBuffer.flip();
        while (byteBuffer.position() < byteBuffer.limit()) {
            int stateId = 0;
            byteBuffer.mark();
            while (stateId != -1 && byteBuffer.position() < byteBuffer.limit()) {
                if (transitionTable.isAccpetedId(stateId) != null) {
                    stack.clear();
                }
                stack.push(stateId);
                char c = byteBuffer.get();
                stateId = transitionTable.getNextState(stateId, c);
            }

            while (transitionTable.isAccpetedId(stateId) == null) {
                try {
                    stateId = stack.pop();
                } catch (EmptyStackException e) {
                    break;
                }
                //rollback position
                if (byteBuffer.position() > 0) {
                    byteBuffer.position(byteBuffer.position() - 1);
                } else {
                    break;
                }
            }

            NFA.State accpetedState = transitionTable.isAccpetedId(stateId);
            if (accpetedState != null) {
                int position = byteBuffer.position();
                int originPosition = byteBuffer.reset().position();
                char[] destinationArr = new char[position - originPosition];
                byteBuffer.get(destinationArr);
                byteBuffer.position(position);

                String tokenValue = new String(destinationArr);

                Token token = new Token();
                token.setName(accpetedState.getTokenType());
                token.setValue(tokenValue);

                tokens.add(token);
            } else if (byteBuffer.position() < byteBuffer.limit()) {
                byteBuffer.position(byteBuffer.position() + 1);
            }
        }
        return this;
    }

    public BaseHandler setOriginStr(String originStr) {
        this.originStr = originStr;
        return this;
    }


    @Override
    public Object getResult() {
        return tokens;
    }
}
