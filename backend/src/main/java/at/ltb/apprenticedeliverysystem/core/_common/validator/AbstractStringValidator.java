package at.ltb.apprenticedeliverysystem.core._common.validator;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractStringValidator {

    protected void validate(String... stringsToValidate) {
        Arrays.stream(stringsToValidate)
                .filter(this::checkString)
                .forEach(item -> throwException());
    }

    private boolean checkString(String s) {
        return Objects.isNull(s) || s.isEmpty() || s.isBlank();
    }

    protected abstract void throwException();
}
