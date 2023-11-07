package ru.studentproject.startbusiness.config;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;


public class FieldMatchValidator implements ConstraintValidator<FieldMatch,Object> {
    private String firstField;
    private String secondField;
    private String message;



    @Override
    public void initialize(final FieldMatch constraintAnnotation){
        firstField = constraintAnnotation.first();
        secondField = constraintAnnotation.second();
        message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(final Object o, final ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        try{
            final Object firstObj = BeanUtils.getProperty(o,firstField);
            final Object secondObj = BeanUtils.getProperty(o,secondField);
            valid = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
        }catch (final Exception ignored){

        }
        if (!valid){
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }
}
