package com.example.abw.utils.pageable;

import com.example.abw.entities.exception.ValidationError;
import com.example.abw.exception.validation.ValidationException;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.model.pageable.sort_kind.SortKind;
import com.example.abw.validator.MyValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


public class PageableUtil {

    public static Pageable getPageable(PageableParams pageableParams) throws ValidationException {
        if (pageableParams != null) {
            Validator validator = MyValidator.getValidator();
            Set<ConstraintViolation<PageableParams>> violations = validator.validate(pageableParams);
            if (violations.size() < 1) {
                Pageable pageable;
                if (pageableParams.getSortKind() == SortKind.ASC) {
                    pageable = PageRequest.of(pageableParams.getPage(),
                            pageableParams.getSize(), Sort.by(pageableParams.getFilter()).ascending());
                } else {
                    pageable = PageRequest.of(pageableParams.getPage(),
                            pageableParams.getSize(), Sort.by(pageableParams.getFilter()).descending());
                }
                return pageable;
            }else {
                ValidationError validationError = new ValidationError();
                for (ConstraintViolation<PageableParams> violation : violations) {
                    validationError.addError(violation.getMessage());
                }
                throw new ValidationException("ValidationException", validationError);
            }

        }else throw new ValidationException("params must not be null");
    }
}
