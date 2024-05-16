package BusinessLogic.validators;

/**
 * The Validator interface defines a method for validating objects of type T.
 * @author Sarafinceanu Denisa
 * @param <T> The type of object to be validated.
 */
public interface Validator<T> {
    /**
     * Validates the given object.
     * @param t The object to be validated.
     */
    public void validate(T t);
}
