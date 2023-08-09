package tj.dfns.model;

public final class CreateUserActionSignaturePayload {
    public final String userActionPayload;
    public final String userActionHttpMethod;
    public final String userActionHttpPath;

    public CreateUserActionSignaturePayload(final String userActionPayload, final String userActionHttpMethod, final String userActionHttpPath) {
        this.userActionPayload = userActionPayload;
        this.userActionHttpMethod = userActionHttpMethod;
        this.userActionHttpPath = userActionHttpPath;
    }
}
