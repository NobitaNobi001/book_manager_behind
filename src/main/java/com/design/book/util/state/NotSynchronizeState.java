package com.design.book.util.state;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author ezuy
 * @date 21/6/7 10:20
 */
@Primary
@Component
public class NotSynchronizeState extends State {

    private Activity activity;

    public NotSynchronizeState(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean changeState() {
        return false;
    }
}