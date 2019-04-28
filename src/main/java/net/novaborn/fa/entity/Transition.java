package net.novaborn.fa.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-27
 * Time: 13:10
 * Description:
 */
@Data
public class Transition {
    private State fromState;
    private State toState;
    private Character character;
}
