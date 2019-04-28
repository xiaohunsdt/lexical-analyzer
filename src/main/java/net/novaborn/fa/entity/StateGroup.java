package net.novaborn.fa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-27
 * Time: 10:25
 * Description:
 */
@Data
@AllArgsConstructor
public class StateGroup {
    private State startState;
    private State endState;
}
