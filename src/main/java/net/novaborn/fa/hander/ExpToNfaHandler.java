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

            if (thisChar == ')' || thisChar == ']') {
                blockDepth--;
                if (blockDepth == 0) {
                    subRegularArray.add(temp);
                    temp = null;
                    continue;
                }
            }

            if (blockDepth > 0) {
                temp = temp.concat(String.valueOf(thisChar));
                if (thisChar == '(' || thisChar == '[') {
                    blockDepth++;
                }
                continue;
            }

            if (thisChar == '(') {
                blockDepth++;
                if (temp == null) {
                    temp = new String();
                }
            } else if (thisChar == '[') {
                blockDepth++;
                if (temp == null) {
                    temp = new String();
                }
                temp = temp.concat(String.valueOf(thisChar));
            } else if (thisChar == '|') {
                subRegularArray.add(String.valueOf(thisChar));
            } else if (thisChar == '*') {
                subRegularArray.add(String.valueOf(thisChar));
            } else {
                subRegularArray.add(String.valueOf(thisChar));
            }
        }

        List<Object> actionChain = new ArrayList<>();
        for (String str : subRegularArray) {
            if (!str.equals("|") && !str.equals("*") && !str.equals("?")) {
                StateGroup stateGroup = handle(str);
                actionChain.add(stateGroup);
            } else {
                actionChain.add(str);
            }
        }
        for (int x = 0; x < actionChain.size(); x++) {
            for (int i = 0; i < actionChain.size(); i++) {
                Object thisElement = actionChain.get(i);
                Object previousStateGroup;
                Object nextStateGroup;
                int y = 0;
                do {
                    if (i - ++y >= 0) {
                        previousStateGroup = actionChain.get(i - y);
                    } else {
                        previousStateGroup = null;
                    }
                } while (previousStateGroup != null && !(previousStateGroup instanceof StateGroup));

                y = 0;
                do {
                    if (i + ++y < actionChain.size()) {
                        nextStateGroup = actionChain.get(i + y);
                    } else {
                        nextStateGroup = null;
                    }
                } while (nextStateGroup != null && !(nextStateGroup instanceof StateGroup));

                //------------------------------------------
                if (thisElement instanceof String) {
                    if (thisElement.equals("|")) {
                        State selectStartState = this.nfa.creatNewState();
                        State selectEndState = this.nfa.creatNewState();

                        StateGroup selectStateGroup = new StateGroup(selectStartState, selectEndState);
                        this.nfa.addTransitionFunc(selectStartState, ((StateGroup) previousStateGroup).getStartState(), null);
                        this.nfa.addTransitionFunc(selectStartState, ((StateGroup) nextStateGroup).getStartState(), null);
                        this.nfa.addTransitionFunc(((StateGroup) previousStateGroup).getEndState(), selectEndState, null);
                        this.nfa.addTransitionFunc(((StateGroup) nextStateGroup).getEndState(), selectEndState, null);
                        actionChain.add(i - 1, selectStateGroup);
                        actionChain.remove(i);
                        actionChain.remove(i);
                        actionChain.remove(i);
                        break;
                    } else if (thisElement.equals("*")) {
                        State jumpStartState = this.nfa.creatNewState();
                        State jumpEndState = this.nfa.creatNewState();
                        State previousStart = ((StateGroup) previousStateGroup).getStartState();
                        State previousEnd = ((StateGroup) previousStateGroup).getEndState();

                        StateGroup jumpStateGroup = new StateGroup(jumpStartState, jumpEndState);
                        this.nfa.addTransitionFunc(previousEnd, previousStart, null);
                        this.nfa.addTransitionFunc(previousEnd, jumpEndState, null);
                        this.nfa.addTransitionFunc(jumpStartState, jumpEndState, null);
                        this.nfa.addTransitionFunc(jumpStartState, previousStart, null);
                        actionChain.add(i - 1, jumpStateGroup);
                        actionChain.remove(i);
                        actionChain.remove(i);
                        break;
                    } else if (thisElement.equals("?")) {
                        State maybeStartState = this.nfa.creatNewState();
                        State maybeEndState = this.nfa.creatNewState();
                        State previousStart = ((StateGroup) previousStateGroup).getStartState();
                        State previousEnd = ((StateGroup) previousStateGroup).getEndState();

                        StateGroup maybeStateGroup = new StateGroup(maybeStartState, maybeEndState);
                        this.nfa.addTransitionFunc(previousEnd, maybeEndState, null);
                        this.nfa.addTransitionFunc(maybeStartState, previousStart, null);
                        this.nfa.addTransitionFunc(maybeStartState, maybeEndState, null);
                        actionChain.add(i - 1, maybeStateGroup);
                        actionChain.remove(i);
                        actionChain.remove(i);
                        break;
                    }
                }
            }
        }


        for (int i = 0; i < actionChain.size(); i++) {
            StateGroup thisStateGroup = (StateGroup) actionChain.get(i);
            StateGroup nextStateGroup = null;

            if (i + 1 < actionChain.size()) {
                nextStateGroup = (StateGroup) actionChain.get(i + 1);
            }

            if (start == null) {
                start = thisStateGroup.getStartState();
                end = thisStateGroup.getEndState();
            }

            if (nextStateGroup != null) {
                this.nfa.addTransitionFunc(end, nextStateGroup.getStartState(), null);
                end = nextStateGroup.getEndState();
            }
        }
        return new StateGroup(start, end);
    }

    @Override
    public Object getResult() {
        return nfa;
    }
}
