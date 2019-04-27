package net.novaborn.fa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-10
 * Time: 13:34
 * Description:
 */
@Data
@AllArgsConstructor
public class StateGroup {
    private NFA.State startState;
    private NFA.State endState;
}
