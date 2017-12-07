package uk.org.oldelvet.examples.thymeleaf1;

public class JsonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final boolean forceCharset;

    public JsonException(boolean force) {
        this.forceCharset = force;
    }

    /**
     * @return the forceCharset
     */
    public boolean isForceCharset() {
        return forceCharset;
    }
}
