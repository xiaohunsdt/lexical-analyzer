package net.novaborn.fa.entity;

import lombok.Data;

import java.util.List;

@Data
public class Transition {
    private State fromState;
    private State toState;
    private List<Character> characters;
}
