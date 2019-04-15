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
        StateGroup stateGroup = this.handle(expression.toCharArray());
        State start = stateGroup.getStartState();
        State end = stateGroup.getEndState();
        end.setIsAccpeted(true);

        // connect state        first ----> start------>end
        nfa.addTransitionFunc(firstState, start, null);
        nfa.addAccpetStates(end);
        return this;
    }

    private StateGroup handle(char[] regularArray) {
        State start = this.nfa.creatNewState();
        State end = this.nfa.creatNewState();
        List<Character> subRegularArray = new ArrayList<>();

        //single character:  start1 ----- reg -----> end1
        if (regularArray.length == 1) {
            nfa.addTransitionFunc(start, end, regularArray[0]);
            return new StateGroup(start, end);
        } else if (regularArray.length > 1) {
            for (int i = 0; i < regularArray.length; i++) {
                char thisChar = regularArray[i];
                char nextChar = 0;
                if (i < regularArray.length - 1) {
                    nextChar = regularArray[i + 1];
                }
                if (thisChar != '(' && thisChar != ')' && thisChar != '|' && thisChar != '*') {
                    if (nextChar != '(' && nextChar != ')' && nextChar != '|' && nextChar != '*') {
                        StateGroup stateGroup = handle(String.valueOf(regularArray[i]).toCharArray());
                        nfa.addTransitionFunc(end, stateGroup.getStartState(), null);
                        end = stateGroup.getEndState();
                        continue;
                    }
                }
            }
            // user null to connect states
            // start1--- a --->end1--- null --->start2---> b --->end2
            char[] temp = new char[subRegularArray.size()];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = subRegularArray.get(i);
            }
            StateGroup stateGroup = handle(temp);
            //
            nfa.addTransitionFunc(end, stateGroup.getStartState(), null);

        }

        return new StateGroup(start, end);
    }

    @Override
    public Object getResult() {
        return nfa;
    }
}
