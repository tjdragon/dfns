
package tj.dfns.gen.model.policies.neo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Filter {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("walletIds")
    @Expose
    private List<String> walletIds = new ArrayList<String>();

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getWalletIds() {
        return walletIds;
    }

    public void setWalletIds(List<String> walletIds) {
        this.walletIds = walletIds;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Filter.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("kind");
        sb.append('=');
        sb.append(((this.kind == null)?"<null>":this.kind));
        sb.append(',');
        sb.append("walletIds");
        sb.append('=');
        sb.append(((this.walletIds == null)?"<null>":this.walletIds));
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
        result = ((result* 31)+((this.walletIds == null)? 0 :this.walletIds.hashCode()));
        result = ((result* 31)+((this.kind == null)? 0 :this.kind.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Filter) == false) {
            return false;
        }
        Filter rhs = ((Filter) other);
        return (((this.walletIds == rhs.walletIds)||((this.walletIds!= null)&&this.walletIds.equals(rhs.walletIds)))&&((this.kind == rhs.kind)||((this.kind!= null)&&this.kind.equals(rhs.kind))));
    }

}
