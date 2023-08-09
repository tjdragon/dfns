package tj.dfns.model;

public final class ClientData {
    public final String type = "key.get";
    public final String challenge;
    public final String origin = "Geneva";
    public final boolean crossOrigin = false;

    public ClientData(final String challenge) {
        this.challenge = challenge;
    }
}
