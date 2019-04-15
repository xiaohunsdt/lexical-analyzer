package net.novaborn.fa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    public class State {
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
        List<StringBuffer> lines = new ArrayList<>();
        Map<State, List<Transition>> fromStateMap = this.getTransitionFuncs().stream()
                .collect(Collectors.groupingBy(Transition::getFromState));
        fromStateMap.forEach((state, funcs) -> {
            if (lines.size() < (funcs.size() - 1) * 2 + 1) {
                lines.add(new StringBuffer());
            }
            for (int i = 0; i < funcs.size(); i++) {
                StringBuffer sf = lines.get(i);

            }
        });
        return String.join("\r\n", lines);
    }
}
