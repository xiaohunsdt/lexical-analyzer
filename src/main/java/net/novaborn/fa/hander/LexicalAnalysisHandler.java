package net.novaborn.fa.hander;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.novaborn.entity.Token;
import net.novaborn.fa.entity.DFA;
import net.novaborn.fa.entity.NFA;
import net.novaborn.fa.entity.NFA.State;
import net.novaborn.fa.entity.Transition;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-27
 * Time: 13:34
 * Description:
 */
public class LexicalAnalysisHandler implements BaseHandler {
    private int [] [] transitionTable;
    private List<Token> tokens = new ArrayList<>();

    public LexicalAnalysisHandler(int[][] transitionTable) {
        this.transitionTable = transitionTable;
    }

    @Override
    public BaseHandler handle() {
        createTransitionTable();
        return this;
    }

    private void createTransitionTable(){

    }

    @Override
    public Object getResult() {
        return tokens;
    }
}
