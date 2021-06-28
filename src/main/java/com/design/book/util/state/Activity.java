package com.design.book.util.state;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ezuy
 * @date 21/6/7 10:21
 */
@Data
@Component
public class Activity {

    @Autowired
    private State state;
    @Autowired
    private State synchronizeState;
    @Autowired
    private State notSynchronizeState;

    public boolean getState(boolean flag) {
        if (flag){
            state = getSynchronizeState();
        }else{
            state = getNotSynchronizeState();
        }
        return state.changeState();
    }
}