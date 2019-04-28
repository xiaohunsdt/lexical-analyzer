package net.novaborn.fa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public
class ClosureState implements Comparable<ClosureState> {
    /**
     * new state of stateList for dfa!
     * ex) q0 q1 q2
     */
    State state;
    /**
     * ex) q0 -> {s0}, q1 -> {s1,s2,s3}
     */
    Set<State> stateList;

    @Override
    public int compareTo(ClosureState o) {
        return this.getState().compareTo(o.getState());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClosureState closureState = (ClosureState) o;
        return this.getState().equals(closureState.getState());
    }
}
