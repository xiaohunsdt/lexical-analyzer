package net.novaborn.fa.hander;

import net.novaborn.fa.entity.NFA;
import net.novaborn.fa.entity.State;

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
        Stack<Character> opreation = new Stack<>();
        for(Character character : expression.toCharArray()){
            if(character.equals("(")){
                opreation.push(character);
            }else if(character.equals(")")) {
                opreation.clear();
            }else if(character.equals("*")){

            }
        }

        return this;
    }

    @Override
    public Object getResult() {
        return nfa;
    }
}
