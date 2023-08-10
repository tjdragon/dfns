
package tj.dfns.gen.model.useractionsig;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CredentialAssertion {

    @SerializedName("credId")
    @Expose
    private String credId;
    @SerializedName("clientData")
    @Expose
    private String clientData;
    @SerializedName("signature")
    @Expose
    private String signature;

    public String getCredId() {
        return credId;
    }

    public void setCredId(String credId) {
        this.credId = credId;
    }

    public String getClientData() {
        return clientData;
    }

    public void setClientData(String clientData) {
        this.clientData = clientData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CredentialAssertion.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("credId");
        sb.append('=');
        sb.append(((this.credId == null)?"<null>":this.credId));
        sb.append(',');
        sb.append("clientData");
        sb.append('=');
        sb.append(((this.clientData == null)?"<null>":this.clientData));
        sb.append(',');
        sb.append("signature");
        sb.append('=');
        sb.append(((this.signature == null)?"<null>":this.signature));
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
        result = ((result* 31)+((this.clientData == null)? 0 :this.clientData.hashCode()));
        result = ((result* 31)+((this.credId == null)? 0 :this.credId.hashCode()));
        result = ((result* 31)+((this.signature == null)? 0 :this.signature.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CredentialAssertion) == false) {
            return false;
        }
        CredentialAssertion rhs = ((CredentialAssertion) other);
        return ((((this.clientData == rhs.clientData)||((this.clientData!= null)&&this.clientData.equals(rhs.clientData)))&&((this.credId == rhs.credId)||((this.credId!= null)&&this.credId.equals(rhs.credId))))&&((this.signature == rhs.signature)||((this.signature!= null)&&this.signature.equals(rhs.signature))));
    }

}
