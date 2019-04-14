package net.novaborn.fa.hander;

import net.novaborn.fa.entity.NFA;
import net.novaborn.fa.entity.State;
import net.novaborn.fa.entity.Transition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ExpToNfaHandler implements BaseHandler {
    private String expression;

    private NFA nfa;

    public ExpToNfaHandler(String regularExp) {
        this.expression = regularExp;
    }

    @Override
    public BaseHandler handle() {
        //init nfa and state;
        nfa = new NFA();
        State startState = new State(0);
        nfa.setStartState(startState);
        nfa.addState(startState);

        //conversion reg to states of nfa
        this.handle(expression, startState);

        return this;
    }

    private BaseHandler handle(String regularExp, State previousState) {
        //conversion reg to states of nfa
        Stack<Character> opreation = new Stack<>();
        Stack<State> stateStack = new Stack<>();
        for (Character character : expression.toCharArray()) {
            if (character.equals("(")) {
                opreation.push(character);
            } else if (character.equals(")")) {
                opreation.clear();
            } else if (character.equals("*")) {
                State firstState = stateStack.firstElement();
            } else {
                State thisState = new State(previousState.getId() + 1);
                Transition transition = new Transition();
                transition.setCharacters(Arrays.asList(character));
                transition.setFromState(previousState);
                transition.setToState(thisState);
                this.nfa.addTransitionFunc(transition);
            }
        }

        return this;
    }

    @Override
    public Object getResult() {
        return nfa;
    }
}
