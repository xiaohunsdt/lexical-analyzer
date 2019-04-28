package net.novaborn.fa.handler;

import net.novaborn.entity.Token;
import net.novaborn.entity.TokenType;
import net.novaborn.fa.entity.DFA;
import net.novaborn.fa.entity.NFA;
import net.novaborn.fa.entity.State;
import net.novaborn.fa.entity.TransitionTable;
import net.novaborn.regular.TokenExpressionList;
import net.novaborn.regular.entity.TokenExpression;

import java.nio.CharBuffer;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-27
 * Time: 14:36
 * Description:
 */
public class LexicalAnalysisHandler implements BaseHandler {
    private Map<TokenType, TransitionTable> transitionTables = new HashMap<>();
    private String originStr;
    private List<Token> tokens = new ArrayList<>();

    public LexicalAnalysisHandler() {
        for (TokenExpression tokenExpression : TokenExpressionList.getExpressions()) {
            ExpToNfaHandler expToNfaHandler = new ExpToNfaHandler();
            NFA nfa = (NFA) expToNfaHandler.setRegExpression(tokenExpression.getExpression()).handle().getResult();

            NfaToDfaHandler nfaToDfaHandler = new NfaToDfaHandler(nfa);
            DFA dfa = (DFA) nfaToDfaHandler.handle().getResult();

            DfaToTransitionTableHandler dfaToTransitionTableHandler = new DfaToTransitionTableHandler(dfa);
            TransitionTable transitionTable = (TransitionTable) dfaToTransitionTableHandler.handle().getResult();
            transitionTables.put(tokenExpression.getType(), transitionTable);
        }
    }

    @Override
    public BaseHandler handle() {
        //Longest matching algorithm by stack
        Stack<Integer> stack = new Stack<>();
        CharBuffer byteBuffer = CharBuffer.allocate(originStr.length()).put(originStr);
        byteBuffer.flip();
        while (byteBuffer.position() < byteBuffer.limit()) {
            boolean hasMatched = false;
            for (Map.Entry<TokenType, TransitionTable> entry : transitionTables.entrySet()) {
                TokenType tokenType = entry.getKey();
                TransitionTable transitionTable = entry.getValue();

                //init state id is 0
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

                State accpetedState = transitionTable.isAccpetedId(stateId);
                if (accpetedState != null) {
                    int position = byteBuffer.position();
                    int originPosition = byteBuffer.reset().position();
                    char[] destinationArr = new char[position - originPosition];
                    byteBuffer.get(destinationArr);
                    byteBuffer.position(position);

                    String tokenValue = new String(destinationArr);

                    Token token = new Token();
                    token.setName(tokenType);
                    token.setValue(tokenValue);

                    tokens.add(token);
                    hasMatched = true;
                    break;
                }
            }

            if (!hasMatched && byteBuffer.position() < byteBuffer.limit()) {
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
