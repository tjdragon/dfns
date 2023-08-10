
package tj.dfns.gen.model.useractionsig;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class UserActionSignature {

    @SerializedName("challengeIdentifier")
    @Expose
    private String challengeIdentifier;
    @SerializedName("firstFactor")
    @Expose
    private FirstFactor firstFactor;

    public String getChallengeIdentifier() {
        return challengeIdentifier;
    }

    public void setChallengeIdentifier(String challengeIdentifier) {
        this.challengeIdentifier = challengeIdentifier;
    }

    public FirstFactor getFirstFactor() {
        return firstFactor;
    }

    public void setFirstFactor(FirstFactor firstFactor) {
        this.firstFactor = firstFactor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserActionSignature.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("challengeIdentifier");
        sb.append('=');
        sb.append(((this.challengeIdentifier == null)?"<null>":this.challengeIdentifier));
        sb.append(',');
        sb.append("firstFactor");
        sb.append('=');
        sb.append(((this.firstFactor == null)?"<null>":this.firstFactor));
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
        result = ((result* 31)+((this.firstFactor == null)? 0 :this.firstFactor.hashCode()));
        result = ((result* 31)+((this.challengeIdentifier == null)? 0 :this.challengeIdentifier.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserActionSignature) == false) {
            return false;
        }
        UserActionSignature rhs = ((UserActionSignature) other);
        return (((this.firstFactor == rhs.firstFactor)||((this.firstFactor!= null)&&this.firstFactor.equals(rhs.firstFactor)))&&((this.challengeIdentifier == rhs.challengeIdentifier)||((this.challengeIdentifier!= null)&&this.challengeIdentifier.equals(rhs.challengeIdentifier))));
    }

}
