package infrastructure;


import com.codingmogul.microservices.userservice.infrastructure.validator.RequestFieldValidateException;
import com.codingmogul.microservices.userservice.infrastructure.validator.RequestValidator;
import com.codingmogul.microservices.userservice.ui.dto.UserRegisterRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestValidatorTests {

    @Test (expected = RequestFieldValidateException.class)
    public void should_return_the_exception_when_validation_fail() throws RequestFieldValidateException {
        var request = new UserRegisterRequest("",null,"game4284@gmail.com");
        RequestValidator.checkNullFields(request);

    }


}
