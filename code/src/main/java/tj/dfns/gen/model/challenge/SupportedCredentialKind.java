
package tj.dfns.gen.model.challenge;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class SupportedCredentialKind {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("factor")
    @Expose
    private String factor;
    @SerializedName("requiresSecondFactor")
    @Expose
    private Boolean requiresSecondFactor;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public Boolean getRequiresSecondFactor() {
        return requiresSecondFactor;
    }

    public void setRequiresSecondFactor(Boolean requiresSecondFactor) {
        this.requiresSecondFactor = requiresSecondFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SupportedCredentialKind.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("kind");
        sb.append('=');
        sb.append(((this.kind == null)?"<null>":this.kind));
        sb.append(',');
        sb.append("factor");
        sb.append('=');
        sb.append(((this.factor == null)?"<null>":this.factor));
        sb.append(',');
        sb.append("requiresSecondFactor");
        sb.append('=');
        sb.append(((this.requiresSecondFactor == null)?"<null>":this.requiresSecondFactor));
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
        result = ((result* 31)+((this.factor == null)? 0 :this.factor.hashCode()));
        result = ((result* 31)+((this.kind == null)? 0 :this.kind.hashCode()));
        result = ((result* 31)+((this.requiresSecondFactor == null)? 0 :this.requiresSecondFactor.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SupportedCredentialKind) == false) {
            return false;
        }
        SupportedCredentialKind rhs = ((SupportedCredentialKind) other);
        return ((((this.factor == rhs.factor)||((this.factor!= null)&&this.factor.equals(rhs.factor)))&&((this.kind == rhs.kind)||((this.kind!= null)&&this.kind.equals(rhs.kind))))&&((this.requiresSecondFactor == rhs.requiresSecondFactor)||((this.requiresSecondFactor!= null)&&this.requiresSecondFactor.equals(rhs.requiresSecondFactor))));
    }

}
