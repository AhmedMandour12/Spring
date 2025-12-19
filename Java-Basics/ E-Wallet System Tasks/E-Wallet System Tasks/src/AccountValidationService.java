
public interface AccountValidationService {

    void validateUsername(String username);

    void validatePassword(String password);

    void validateAge(int age);

    void validatePhone(String phone);
}