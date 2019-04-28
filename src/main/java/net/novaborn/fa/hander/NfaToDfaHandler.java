package net.novaborn.fa.hander;

import net.novaborn.fa.entity.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-27
 * Time: 14:34
 * Description:
 */
public class NfaToDfaHandler implements BaseHandler {
    private NFA nfa;
    private DFA dfa;
    /**
     * all of ClosureState save in this list.
     * It's core data of Subset Construction Algorithms
     */
    private List<ClosureState> closureStates = new ArrayList<>();

    public NfaToDfaHandler(NFA nfa) {
        this.nfa = nfa;
        this.dfa = new DFA();
    }

    @Override
    public BaseHandler handle() {
        Queue<ClosureState> queue = new PriorityQueue();
        ClosureState q0 = epsClosure(nfa.getStartState());
        closureStates.add(q0);

        queue.add(q0);

        while (queue.size() > 0) {
            ClosureState q = queue.remove();
            for (State subState : q.getStateList()) {
                List<Transition> transitions = nfa.getTransition(subState);
                //filter ε transition
                transitions = transitions.stream().filter(transition -> !transition.getCharacter().equals('ε')).collect(Collectors.toList());

                if (transitions.size() > 0) {
                    transitions.forEach(transition -> {
                        ClosureState newQ = epsClosure(transition.getToState());
                        //add new transition for dfa;
                        dfa.addTransitionFunc(q.getState(), newQ.getState(), transition.getCharacter());
                        if (!closureStates.contains(newQ)) {
                            closureStates.add(newQ);
                            queue.add(newQ);
                        }
                    });
                }
            }
        }

        return this;
    }

    /**
     * use Recursive acquisition closure
     *
     * @return
     */
    private ClosureState epsClosure(State state) {
        Set<State> stateList = new HashSet<>();
        epsClosure(state, stateList);
        ClosureState hasExistClosureState = closureSubListHasExist(stateList);
        if (hasExistClosureState != null) {
            return hasExistClosureState;
        } else {
            State newState = dfa.creatNewState();
            //make the new state be accpet state in dfa if this epsilon state list have accpet state of nfa
            for (State epsilonState : stateList) {
                if (nfa.getAccpetStates().contains(epsilonState)) {
                    if (newState.getTokenType() != null) {
                        throw new IllegalStateException();
                    }
                    newState.setTokenType(epsilonState.getTokenType());
                    dfa.addAccpetState(newState);
                }
            }
            return new ClosureState(newState, stateList);
        }
    }

    private void epsClosure(State state, Set<State> stateList) {
        stateList.add(state);
        for (State nextState : nfa.getNextEpsilonState(state)) {
            if (!stateList.contains(nextState)) {
                epsClosure(nextState, stateList);
            }
        }
    }

    /**
     * Determine whether the current subset already exists
     * if exist return that closure state
     *
     * @param closureSubList
     * @return
     */
    private ClosureState closureSubListHasExist(Set<State> closureSubList) {
        for (ClosureState closureState : closureStates) {
            if (equalList(closureState.getStateList(), closureSubList)) {
                return closureState;
            }
        }
        return null;
    }

    public boolean equalList(Set list1, Set list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        return list2.containsAll(list1);
    }


    @Override
    public Object getResult() {
        return dfa;
    }
}
