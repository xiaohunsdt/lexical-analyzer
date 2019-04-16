package net.novaborn.fa.hander;

import net.novaborn.fa.entity.NFA;
import net.novaborn.fa.entity.NFA.State;
import net.novaborn.fa.entity.StateGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpToNfaHandler implements BaseHandler {
    private String expression;

    private NFA nfa;

    Stack<State> stateStack = new Stack<>();

    public ExpToNfaHandler() {
        //init nfa and state;
        nfa = new NFA();
        State startState = this.nfa.creatNewState();
        nfa.setStartState(startState);
        nfa.addState(startState);
    }

    public BaseHandler setRegExpression(String expression) {
        this.expression = expression;
        return this;
    }

    @Override
    public BaseHandler handle() {
        //conversion reg to states of nfa
        State firstState = this.nfa.getStartState();

        //
        StateGroup stateGroup = this.handle(expression);
        State start = stateGroup.getStartState();
        State end = stateGroup.getEndState();

        // connect state        first ----> start------>end
        nfa.addTransitionFunc(firstState, start, null);
        nfa.addAccpetStates(end);
        return this;
    }

    private StateGroup handle(String regularStr) {
        List<String> subRegularArray = new ArrayList<>();
        State start = null;
        State end = null;
        //single character:  start1 ----- reg -----> end1
        if (regularStr.length() == 1) {
            start = this.nfa.creatNewState();
            end = this.nfa.creatNewState();
            nfa.addTransitionFunc(start, end, regularStr.charAt(0));
            return new StateGroup(start, end);
        }


        char[] chars = regularStr.toCharArray();
        String temp = null;
        //catch () inner elements
        int blockDepth = 0;
        for (int i = 0; i < chars.length; i++) {
            char thisChar = chars[i];

            if (thisChar == ')') {
                blockDepth--;
                if (blockDepth == 0) {
                    subRegularArray.add(temp);
                    temp = null;
                    continue;
                }
            }

            if (blockDepth > 0) {
                temp = temp.concat(String.valueOf(thisChar));
                if(thisChar == '('){
                    blockDepth++;
                }
            }else if (thisChar == '(') {
                blockDepth++;
                if (temp == null) {
                    temp = new String();
                }
            } else if (thisChar == '|') {
                subRegularArray.add(String.valueOf(thisChar));
            } else if (thisChar == '*') {
                subRegularArray.add(String.valueOf(thisChar));
            } else {
                subRegularArray.add(String.valueOf(thisChar));
            }
        }

        for (String str : subRegularArray) {
            StateGroup stateGroup = handle(str);
            if (end != null) {
                nfa.addTransitionFunc(end, stateGroup.getStartState(), null);
            } else {
                start = stateGroup.getStartState();
            }
            end = stateGroup.getEndState();
        }
        return new StateGroup(start, end);

    }

    @Override
    public Object getResult() {
        return nfa;
    }
}
