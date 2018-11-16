package auth.exception;

public class UsernameNotValid extends RuntimeException {
	private String resourceUri;

    public UsernameNotValid(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    @Override
    public String getMessage() {
        return String.format("No resource found with (URI : %s)",resourceUri);
    }
}
