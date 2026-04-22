

package com.day3.framework.retry;

import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;

public class RetryExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {

        Method method = context.getRequiredTestMethod();
        Retry retry = method.getAnnotation(Retry.class);

        if (retry == null) throw throwable;

        int maxRetries = retry.value();

        int currentRetry = context.getStore(ExtensionContext.Namespace.GLOBAL)
                .getOrDefault(context.getUniqueId(), Integer.class, 0);

        if (currentRetry < maxRetries) {
            currentRetry++;

            context.getStore(ExtensionContext.Namespace.GLOBAL)
                    .put(context.getUniqueId(), currentRetry);

            System.out.println("🔁 Retry attempt: " + currentRetry);

            throw throwable;
        } else {
            throw throwable;
        }
    }
}