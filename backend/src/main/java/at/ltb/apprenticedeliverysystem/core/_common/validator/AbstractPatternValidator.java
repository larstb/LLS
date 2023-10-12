package at.ltb.apprenticedeliverysystem.core._common.validator;

import java.util.regex.Pattern;

public abstract class AbstractPatternValidator {

    public void validate(String toValidate) {
        if (Boolean.FALSE.equals(validatePattern(toValidate))) {
            throwException();
        }
    }

    private boolean validatePattern(String password) {
        return Pattern.compile(getPattern()).matcher(password).matches();
    }

    protected abstract String getPattern();

    protected abstract void throwException();
}
