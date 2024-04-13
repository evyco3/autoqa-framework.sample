package org.example.framework.utils;

import io.qameta.allure.Allure;
import org.assertj.core.api.AbstractAssert;


public class CustomAssertionsUtils<T> extends AbstractAssert<CustomAssertionsUtils<T>, T> {

    protected CustomAssertionsUtils(T actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public static <T> CustomAssertionsUtils<T> assertThat(T actual) {
        return new CustomAssertionsUtils<>(actual, CustomAssertionsUtils.class);
    }

    public void isEqualTo(T expectedValue, String conditionDescription) {
        ActionExecutor.execAction(getClass(), () -> {
            if (!actual.equals(expectedValue)) {
                failWithMessage("Expected %s to be %s, but it was %s.", conditionDescription, expectedValue, actual);
            } else {
                Allure.step(String.format("Condition met: %s is %s", conditionDescription, expectedValue));
            }
        }, "Condition met: " + conditionDescription + " is " + expectedValue, "Failed to assert equality for " + conditionDescription);
    }

    public void contains(String conditionDescription) {
        ActionExecutor.execAction(getClass(), () -> {
            String actualString = ((String) actual).toLowerCase();
            if (!actualString.contains(conditionDescription.toLowerCase())) {
                failWithMessage("Expected %s to contain %s, but it did not.", conditionDescription, conditionDescription);
            } else {
                Allure.step(String.format("Condition met: %s contains %s", conditionDescription, conditionDescription.toLowerCase()));
            }
        }, "Condition met: " + conditionDescription + " contains " + conditionDescription.toLowerCase(), "Failed to assert contains for " + conditionDescription);
    }
}
