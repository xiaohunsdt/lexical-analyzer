package net.novaborn.fa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.novaborn.entity.TokenType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-10
 * Time: 13:34
 * Description:
 */
@Data
public class NFA {
    //id of state, auto increment when create a new state
    private int index;
    private State startState;
    private List<State> stetes = new ArrayList<>();
    private List<State> accpetStates = new ArrayList<>();
    List<Transition> transitionFuncs = new ArrayList<>();

    public State creatNewState() {
        State state = new State(index++);
        this.addState(state);
        return state;
    }

    public boolean addState(State state) {
        try {
            stetes.add(state);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addAccpetState(State accpetState) {
        try {
            accpetState.setAccpeted(true);
            accpetStates.add(accpetState);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addTransitionFunc(State from, State to, Character character) {
        if(character == null){
            character = 'ε';
        }
        try {
            Transition transitionFunc = new Transition();
            transitionFunc.setFromState(from);
            transitionFunc.setToState(to);
            transitionFunc.setCharacter(character);
            transitionFuncs.add(transitionFunc);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * return a list of transition
     * @param fromState
     * @return
     */
    public List<Transition> getTransition(State fromState) {
        List<Transition> results = new ArrayList<>();
        for (Transition transition : transitionFuncs) {
            if (transition.getFromState() == fromState) {
                results.add(transition);
            }
        }
        return results;
    }

    /**
     * return a epsilon state from a state by transition
     *
     * @param fromState
     * @return
     */
    public List<State> getNextEpsilonState(State fromState) {
        List<State> results = new ArrayList<>();
        for (Transition transition : transitionFuncs) {
            if (transition.getFromState() == fromState && transition.getCharacter().equals('ε')) {
                results.add(transition.getToState());
            }
        }
        return results;
    }

    public class State implements Comparable<State> {
        @Getter
        private Integer id;
        @Getter @Setter
        private boolean isStart;
        @Getter @Setter
        private boolean isAccpeted;
        @Getter @Setter
        private TokenType tokenType;

        private State(Integer id) {
            this.id = id;
            if (id == 0) {
                isStart = true;
            }
        }

        @Override
        public int compareTo(State o) {
            return this.getId().compareTo(o.getId());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            State state = (State) o;
            return id.equals(state.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    @Override
    public String toString() {
        StringBuffer line = new StringBuffer();
        List<Transition> transitions = this.transitionFuncs;
        transitions.stream().sorted(Comparator.comparing(Transition::getFromState)).forEach(func -> {
            line.append("S");
            line.append(func.getFromState().getId());
            line.append(" ---- ");
            line.append(func.getCharacter());
            line.append(" ---> ");
            line.append("S");
            line.append(func.getToState().getId());
            if (func.getToState().isAccpeted()) {
                line.append("(end)");
            }
            line.append("\r\n");
        });
        return line.toString();
    }
}
