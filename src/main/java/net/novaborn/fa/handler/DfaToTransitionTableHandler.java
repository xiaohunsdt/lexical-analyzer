package net.novaborn.fa.handler;

import net.novaborn.fa.entity.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-27
 * Time: 14:35
 * Description:
 */
public class DfaToTransitionTableHandler implements BaseHandler {
    private DFA dfa;
    TransitionTable transitionTable;

    public DfaToTransitionTableHandler(DFA dfa) {
        this.dfa = dfa;
    }

    @Override
    public BaseHandler handle() {
        Set<Character> keywords = dfa.getTransitionFuncs().stream()
                .filter(transition -> !transition.getCharacter().equals('ε'))
                .map(Transition::getCharacter)
                .collect(Collectors.toSet());
        State[] accpetedIds = dfa.getAccpetStates().toArray(new State[0]);
        int rowCount = dfa.getStetes().size();
        int columnCount = keywords.size();

        transitionTable = new TransitionTable();
        transitionTable.setKeywordTable(keywords.toArray(new Character[0]));
        transitionTable.setTransitionTable(new Integer[rowCount][columnCount]);
        transitionTable.setAccpeteds(accpetedIds);

        for (Transition transition : dfa.getTransitionFuncs()){
            int row = transition.getFromState().getId();
            int column = transitionTable.getCharacterIndex(transition.getCharacter());
            if(transitionTable.getTransitionTable()[row][column] != null){
                throw new IllegalStateException();
            }
            transitionTable.getTransitionTable()[row][column] = transition.getToState().getId();
        }
        return this;
    }

    @Override
    public Object getResult() {
        return transitionTable;
    }
}
