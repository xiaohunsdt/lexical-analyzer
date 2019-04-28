package net.novaborn.fa.hander;

import net.novaborn.entity.Token;
import net.novaborn.fa.entity.TransitionTable;

import java.nio.CharBuffer;
import java.util.ArrayList;
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
        int stateId = 0;

        //Longest matching algorithm by stack
        Stack<Integer> stack = new Stack<>();
        CharBuffer byteBuffer = CharBuffer.allocate(originStr.length()).put(originStr);

        while (stateId != -1) {
            char c = byteBuffer.get();
            if (transitionTable.isAccpetedId(stateId)) {
                stack.clear();
            }
            stack.push(stateId);
            stateId = transitionTable.getNextState(stateId, c);
        }

        while (!transitionTable.isAccpetedId(stateId)) {
            stateId = stack.pop();
            //rollback position
            if (byteBuffer.position() > 0){
                byteBuffer.position(byteBuffer.position() - 1);
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
