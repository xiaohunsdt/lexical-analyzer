package net.novaborn.fa.entity;

import lombok.Data;

@Data
public class State {
    private Integer id;
    private Boolean isStart;
    private Boolean isAccpeted;

    public State(Integer id) {
        this.id = id;
        if (id == 0) {
            isStart = true;
        }
    }
}
