package at.ltb.apprenticedeliverysystem.core.user.validator;

import at.ltb.apprenticedeliverysystem.core._common.validator.AbstractPatternValidator;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserPhoneNumberPatternMismatchException;
import org.springframework.stereotype.Component;

@Component
public class UserPhoneNumberValidator extends AbstractPatternValidator {

    @Override
    protected String getPattern() {
        return "^[\\+]43\\d{9,}$";
    }

    @Override
    protected void throwException() {
        throw new UserPhoneNumberPatternMismatchException("phone number matches not pattern");
    }

}
