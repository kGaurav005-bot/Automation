package org.practice.framework.context;

public final class ExecutionContextManager {

    private static final ThreadLocal<ExecutionContext>
            CONTEXT = new ThreadLocal<>();

    private ExecutionContextManager() {
    }

    public static void set(
            ExecutionContext context) {

        CONTEXT.set(context);
    }

    public static ExecutionContext get() {

        return CONTEXT.get();
    }

    public static void clear() {
//This is important.If we don't clear the context Test 2 could accidentally inherit Test 1's context
        CONTEXT.remove();
    }
}