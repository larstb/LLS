package at.ltb.apprenticedeliverysystem.core.user.validator;

import at.ltb.apprenticedeliverysystem.core._common.validator.AbstractPatternValidator;
import at.ltb.apprenticedeliverysystem.core.user._persistence.UserCrudRepository;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserEmailPatternMismatchException;
import at.ltb.apprenticedeliverysystem.core.user.exception.UserEmailUniqueException;
import org.springframework.stereotype.Component;

@Component
public class UserEmailValidator extends AbstractPatternValidator {

    private final UserCrudRepository userRepository;

    public UserEmailValidator(UserCrudRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(String toValidate) {
        super.validate(toValidate);

        if (validateEmailUnique(toValidate)) {
            throw new UserEmailUniqueException("email address is already used");
        }
    }

    public void validateWithoutUniqueCheck(String toValidate) {
        super.validate(toValidate);
    }

    @Override
    protected String getPattern() {
        return "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    }

    @Override
    protected void throwException() {
        throw new UserEmailPatternMismatchException("email address matches not email pattern");
    }

    private boolean validateEmailUnique(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

}
