
package tj.dfns.gen.model.useractionsig;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class FirstFactor {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("credentialAssertion")
    @Expose
    private CredentialAssertion credentialAssertion;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public CredentialAssertion getCredentialAssertion() {
        return credentialAssertion;
    }

    public void setCredentialAssertion(CredentialAssertion credentialAssertion) {
        this.credentialAssertion = credentialAssertion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FirstFactor.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("kind");
        sb.append('=');
        sb.append(((this.kind == null)?"<null>":this.kind));
        sb.append(',');
        sb.append("credentialAssertion");
        sb.append('=');
        sb.append(((this.credentialAssertion == null)?"<null>":this.credentialAssertion));
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
        result = ((result* 31)+((this.kind == null)? 0 :this.kind.hashCode()));
        result = ((result* 31)+((this.credentialAssertion == null)? 0 :this.credentialAssertion.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FirstFactor) == false) {
            return false;
        }
        FirstFactor rhs = ((FirstFactor) other);
        return (((this.kind == rhs.kind)||((this.kind!= null)&&this.kind.equals(rhs.kind)))&&((this.credentialAssertion == rhs.credentialAssertion)||((this.credentialAssertion!= null)&&this.credentialAssertion.equals(rhs.credentialAssertion))));
    }

}
