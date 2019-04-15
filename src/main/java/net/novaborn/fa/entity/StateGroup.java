package net.novaborn.fa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class StateGroup {
    private NFA.State startState;
    private NFA.State endState;
}
