package at.ltb.apprenticedeliverysystem.core.user.validator;

import at.ltb.apprenticedeliverysystem.core._common.validator.AbstractPatternValidator;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserPasswordPatternMismatchException;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordValidator extends AbstractPatternValidator {

    @Override
    protected String getPattern() {
        return "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    }

    @Override
    protected void throwException() {
        throw new UserPasswordPatternMismatchException("password matches not pattern");
    }

}
