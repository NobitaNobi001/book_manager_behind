package com.design.book.util.state;

import org.springframework.stereotype.Component;

/**
 * @author ezuy
 * @date 21/6/7 10:20
 */
@Component
public class SynchronizeState extends State {

    private Activity activity;

    public SynchronizeState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean changeState() {
        return true;
    }
}