package notifications_lib.common.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public final class ValidationConfiguration implements AutoCloseable {

    private final ValidatorFactory factory;
    private final Validator validator;

    public ValidationConfiguration() {
        this.factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public Validator getValidator() {
        return validator;
    }

    @Override
    public void close() {
        factory.close();
    }
}
