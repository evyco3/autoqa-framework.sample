package org.example.framework.utils;

import io.qameta.allure.Allure;

import java.util.function.Supplier;

/**
 * A utility class for managing try-catch blocks in test automation frameworks.
 * Provides methods to execute actions and functions with error handling,
 * logging, and reporting capabilities.
 */

public class ActionExecutor {

    public static <T> void execAction(Class<?> Tclass, Runnable execution, String successMessage, String errorMessage) {
        try {
            execution.run();
            FrameworkLogger.log(Tclass, FrameworkLogger.LogType.INFO, successMessage);
            Allure.step(successMessage);
        } catch (Exception e) {
            String errorMessageWithException = errorMessage + ": " + e.getMessage();
            FrameworkLogger.log(Tclass, FrameworkLogger.LogType.ERROR, errorMessageWithException);
            Allure.step(errorMessageWithException);
            throw new IllegalArgumentException(errorMessage, e);
        }
    }

    public static <T> T execFunction(Class<?> Tclass, Supplier<T> execution, String successMessage, String errorMessage) {
        try {
            T result = execution.get();
            FrameworkLogger.log(Tclass, FrameworkLogger.LogType.INFO, successMessage);
            Allure.step(successMessage);
            return result;
        } catch (Exception e) {
            String errorMessageWithException = errorMessage + ": " + e.getMessage();
            FrameworkLogger.log(Tclass, FrameworkLogger.LogType.ERROR, errorMessageWithException);
            Allure.step(errorMessageWithException);
            throw new IllegalArgumentException(errorMessage, e);
        }
    }



}
