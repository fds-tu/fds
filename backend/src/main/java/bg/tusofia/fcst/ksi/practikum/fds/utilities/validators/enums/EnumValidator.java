package bg.tusofia.fcst.ksi.practikum.fds.utilities.validators.enums;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<EnumValid, Object> {

    private EnumValid annotation;

    @Override
    public void initialize(EnumValid annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return false;

        Object[] enumValues = annotation.enumClass().getEnumConstants();

        return Arrays.stream(enumValues)
                .anyMatch(e -> e.toString().equals(value.toString()));
    }
}