package com.dylanjsa.seleniumj.rules;

import com.dylanjsa.seleniumj.S;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Dylan Janeke on 2017/02/18.
 */
public class TryAgainOnErrorRule implements TestRule {
    private int times;
    private long pauseBetweenRetries = 100;

    public TryAgainOnErrorRule(S s) {
        times = s.getConfig().getDefaultRetries();
    }

    public TryAgainOnErrorRule(S s, int numTimes) {
        this.times = numTimes;
    }

    public TryAgainOnErrorRule(S s, int numTimes, long pause) {
        this.times = numTimes;
        this.pauseBetweenRetries = pause;
    }

    public Statement apply(Statement base, Description description) {
        return statement(base, description);
    }

    private Statement statement(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable theT = null;
                for (int i = 0; i < times; i++) {
                    try {
                        Thread.sleep(pauseBetweenRetries);
                        base.evaluate();
                        return;
                    } catch (Throwable t) {
                        theT = t;
                        t.printStackTrace();
                        System.err.println(description.getDisplayName() + " failed, trying again...");
                    }
                }
                System.err.println(description.getDisplayName() + " failed too many times!");
                throw theT;
            }
        };
    }
}