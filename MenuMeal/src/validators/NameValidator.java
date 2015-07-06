package validators;

public class NameValidator {
    public boolean isNameValid (String name)
    {
        if (name.matches("^[A-Za-z]+$")) {
            return true;
        }
        else
        {
            return false;
        }
    }
}
