package tj.dfns.model.man;

public final class CreateWalletRequest {
    public final String network;
    public final String name;

    public CreateWalletRequest(final String network, final String name) {
        this.network = network;
        this.name = name;
    }
}
