
package tj.dfns.gen.model.wallets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("jsonschema2pojo")
public class WalletAssets {

    @SerializedName("walletId")
    @Expose
    private String walletId;
    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("assets")
    @Expose
    private List<Asset> assets = new ArrayList<Asset>();

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(WalletAssets.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("walletId");
        sb.append('=');
        sb.append(((this.walletId == null)?"<null>":this.walletId));
        sb.append(',');
        sb.append("network");
        sb.append('=');
        sb.append(((this.network == null)?"<null>":this.network));
        sb.append(',');
        sb.append("assets");
        sb.append('=');
        sb.append(((this.assets == null)?"<null>":this.assets));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.walletId == null)? 0 :this.walletId.hashCode()));
        result = ((result* 31)+((this.assets == null)? 0 :this.assets.hashCode()));
        result = ((result* 31)+((this.network == null)? 0 :this.network.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof WalletAssets) == false) {
            return false;
        }
        WalletAssets rhs = ((WalletAssets) other);
        return ((((this.walletId == rhs.walletId)||((this.walletId!= null)&&this.walletId.equals(rhs.walletId)))&&((this.assets == rhs.assets)||((this.assets!= null)&&this.assets.equals(rhs.assets))))&&((this.network == rhs.network)||((this.network!= null)&&this.network.equals(rhs.network))));
    }

}
