package net.novaborn.fa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

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
        private Boolean isStart;
        @Getter
        @Setter
        private Boolean isAccpeted;

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
        transitions.stream().sorted(Comparator.comparing(Transition::getToState)).forEach(func -> {
            line.append("S");
            line.append(func.getFromState().getId());
            line.append(" ---- ");
            line.append(func.getCharacter());
            line.append(" ---> ");
            line.append("S");
            line.append(func.getToState().getId());
            line.append("\r\n");
        });
        return line.toString();
    }
}
