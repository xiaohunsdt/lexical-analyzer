package net.novaborn.fa.entity;

import lombok.Data;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA
 * User: wangyong
 * Date: 2019-04-28
 * Time: 13:34
 * Description:
 */
@Data
public class TransitionTable {
    private Integer[][] transitionTable;
    private Character[] keywordTable;
    private NFA.State[] accpeteds;

    /**
     * get index of specific char in keyword table
     * if specific char have not exist return -1
     *
     * @param keyword
     * @return
     */
    public int getCharacterIndex(char keyword) {
        for (int i = 0; i < keywordTable.length; i++) {
            if (keywordTable[i] == keyword) {
                return i;
            }
        }

        return -1;
    }

    /**
     * get next state by search transition table
     * stateId is the row of transition table
     *
     * @param stateId
     * @param keyword
     * @return
     */
    public int getNextState(int stateId, char keyword) {
        int columnIndex = getCharacterIndex(keyword);
        if (columnIndex == -1) {
//            throw new IllegalArgumentException("keyword have not exist");
            return -1;
        }
        int result = Optional.ofNullable(transitionTable[stateId][columnIndex]).orElse(-1);
        return result;
    }

    /**
     * determine whether the specified state id is accpeted id
     *
     * @param stateId
     * @return
     */
    public NFA.State isAccpetedId(int stateId) {
        if (stateId == -1) {
            return null;
        }

        for (int i = 0; i < accpeteds.length; i++) {
            if (accpeteds[i].getId() == stateId) {
                return accpeteds[i];
            }
        }
        return null;
    }

//    @Override
//    public String toString() {
//        StringBuffer sf = new StringBuffer();
//    }
}
