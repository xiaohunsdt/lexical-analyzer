package net.novaborn.fa.entity;

import lombok.Data;

import java.util.List;

@Data
public class Transition {
    private NFA.State fromState;
    private NFA.State toState;
    private Character character;
}
