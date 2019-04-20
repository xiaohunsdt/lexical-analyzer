package net.novaborn.fa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Data
public class NFA {
    //id of state, auto increment when create a new state
    private int index;
    private List<State> stetes = new ArrayList<>();
    private State startState;
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

    public boolean addAccpetStates(State accpetState) {
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

    public class State implements Comparable<State> {
        @Getter
        private Integer id;
        @Getter
        @Setter
        private boolean isStart;
        @Getter
        @Setter
        private boolean isAccpeted;

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
