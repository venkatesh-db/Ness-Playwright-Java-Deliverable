

package com.day3.framework.retry;

import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;

public class RetryExtension implements InvocationInterceptor {

    @Override
    public void interceptTestMethod(Invocation<Void> invocation,
                                    ReflectiveInvocationContext<Method> context,
                                    ExtensionContext extensionContext) throws Throwable {

        Method method = extensionContext.getRequiredTestMethod();
        Retry retry = method.getAnnotation(Retry.class);

        int maxRetries = (retry != null) ? retry.value() : 1;

        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                attempt++;
                System.out.println("➡️ Retry attempt: " + attempt);

                invocation.proceed(); // 🔥 this runs your test

                return; // success → exit

            } catch (Throwable t) {

                System.out.println("❌ Failed at attempt " + attempt);

                if (attempt >= maxRetries) {
                    throw t;
                }

                System.out.println("🔁 Retrying...");
            }
        }
    }
}



/*

package com.day3.framework.retry;

import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;

public class RetryExtension implements TestExecutionExceptionHandler{

    @Override
    public void handleTestExecutionException(ExtensionContext context ,Throwable throwable  ) throws Throwable
    {

        Method method=context.getRequiredTestMethod();
        Retry retry=  method.getAnnotation(Retry.class);

        if ( retry == null ) throw throwable;
        int maxRetries = retry.value();

       int currentRetry= context.getStore(ExtensionContext.Namespace.GLOBAL).getOrDefault( context.getUniqueId(), Integer.class,0);

        System.out.println("handleTestExecutionException smiles ");

       if (  currentRetry < maxRetries)
       {
           currentRetry++;
           context.getStore(ExtensionContext.Namespace.GLOBAL).put(context.getUniqueId(),currentRetry);
           System.out.println("retry attempt"+currentRetry);

           throw throwable;

       }else {
           throw throwable;
       }

    }

}


*/