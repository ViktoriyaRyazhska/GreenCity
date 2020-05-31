package greencity.validator;

import greencity.annotations.ValidTipsAndTricksDtoRequest;
import greencity.constant.ErrorMessage;
import greencity.dto.tipsandtricks.TipsAndTricksDtoRequest;
import greencity.exception.exceptions.InvalidURLException;
import greencity.service.TipsAndTricksTagsService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import static greencity.validator.UrlValidator.isUrlValid;

public class TipsAndTricksDtoRequestValidator
    implements ConstraintValidator<ValidTipsAndTricksDtoRequest, TipsAndTricksDtoRequest> {
    @Autowired
    private TipsAndTricksTagsService tipsAndTricksTagsService;

    @Override
    public void initialize(ValidTipsAndTricksDtoRequest constraintAnnotation) {
    }

    @Override
    public boolean isValid(TipsAndTricksDtoRequest dto, ConstraintValidatorContext constraintValidatorContext) {
        if (isUrlValid(dto.getSource())) {
            if (tipsAndTricksTagsService.isValidNumOfUniqueTags(dto.getTipsAndTricksTags())) {
                return tipsAndTricksTagsService.isAllValid(dto.getTipsAndTricksTags());
            }
        }
        throw new InvalidURLException(ErrorMessage.INVALID_URL);
    }
}
