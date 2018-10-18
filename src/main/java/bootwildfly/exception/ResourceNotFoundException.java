package bootwildfly.exception;

public class ResourceNotFoundException extends RuntimeException {
    private String resourceUri;

    public ResourceNotFoundException(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    @Override
    public String getMessage() {
        return String.format("No resource found with (URI : %s)",resourceUri);
    }
}
