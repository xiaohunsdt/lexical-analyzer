package net.novaborn.fa.hander;

import net.novaborn.fa.entity.DFA;
import net.novaborn.fa.entity.NFA;
import net.novaborn.fa.entity.Transition;
import net.novaborn.fa.entity.TransitionTable;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-27
 * Time: 13:34
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
        NFA.State[] accpetedIds = dfa.getAccpetStates().toArray(NFA.State[]::new);
        int rowCount = dfa.getStetes().size();
        int columnCount = keywords.size();

        transitionTable = new TransitionTable();
        transitionTable.setKeywordTable(keywords.toArray(Character[]::new));
        transitionTable.setTransitionTable(new Integer[rowCount][columnCount]);
        transitionTable.setAccpeteds(accpetedIds);

        for (Transition transition : dfa.getTransitionFuncs()){
            int row = transition.getFromState().getId();
            int column = transitionTable.getCharacterIndex(transition.getCharacter());
            transitionTable.getTransitionTable()[row][column] = transition.getToState().getId();
        }
        return this;
    }

    @Override
    public Object getResult() {
        return transitionTable;
    }
}
