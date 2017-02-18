package com.dylanjsa.seleniumj.rules;

import com.dylanjsa.seleniumj.S;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.File;

/**
 * Created by Dylan Janeke on 2017/02/18.
 */
public class ErrorScreenshotRule implements TestRule {
    private S s;
    private File dir;

    public ErrorScreenshotRule(S s) {
        dir = s.getConfig().getDefaultScreenshotDir();
    }

    public ErrorScreenshotRule(S s, File dir) {
        this.s = s;
        this.dir = dir;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return statement(base, description);
    }

    private Statement statement(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    base.evaluate();
                    return;
                } catch (Throwable t) {
                    s.takeScreenshot(new File(String.format("%s/%s-%s-error.png", dir.getPath(),
                            description.getClassName(),description.getMethodName())));
                    throw t;
                }
            }
        };
    }
}