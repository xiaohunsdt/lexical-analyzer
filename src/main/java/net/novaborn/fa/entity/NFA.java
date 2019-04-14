package net.novaborn.fa.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NFA {
    private List<State> stetes = new ArrayList<>();
    private State startState;
    private List<State> accpetStates = new ArrayList<>();
    List<Transition> transitionFuncs = new ArrayList<>();

    public boolean addState(State state) {
        try {
            stetes.add(state);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addAccpetStates(State accpetState){
        try {
            accpetStates.add(accpetState);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean addTransitionFunc(Transition transitionFunc){
        try {
            transitionFuncs.add(transitionFunc);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
