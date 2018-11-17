package auth.exception;

public class ClientIDNotValid extends RuntimeException {
	private String resourceUri;

    public ClientIDNotValid(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    @Override
    public String getMessage() {
        return String.format("No resource found with (URI : %s)",resourceUri);
    }
}
