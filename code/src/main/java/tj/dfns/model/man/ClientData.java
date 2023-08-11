package tj.dfns.model.man;

public final class ClientData {
    public final String type = "key.get";
    public final String challenge;
    public final String origin = "https://app.dfns.ninja";
    public final boolean crossOrigin = false;

    public ClientData(final String challenge) {
        this.challenge = challenge;
    }
}
