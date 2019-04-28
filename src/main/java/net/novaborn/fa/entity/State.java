package net.novaborn.fa.entity;

import lombok.Getter;
import lombok.Setter;
import net.novaborn.entity.TokenType;

import java.util.Objects;

public class State implements Comparable<State> {
    @Getter
    private Integer id;
    @Getter @Setter
    private boolean isStart;
    @Getter @Setter
    private boolean isAccpeted;
    @Getter @Setter
    private TokenType tokenType;

    public State(){}

    protected State(Integer id) {
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
