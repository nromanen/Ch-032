package validators;

public class PriceValidator {
    public boolean isPriceValid (double price)
    {
        if (price <= 0) {
            return false;
        }
        else
        {
            return true;
        }
    }
}
