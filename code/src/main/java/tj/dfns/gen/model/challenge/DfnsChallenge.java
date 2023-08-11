
package tj.dfns.gen.model.challenge;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("jsonschema2pojo")
public class DfnsChallenge {

    @SerializedName("supportedCredentialKinds")
    @Expose
    private List<SupportedCredentialKind> supportedCredentialKinds = new ArrayList<SupportedCredentialKind>();
    @SerializedName("challenge")
    @Expose
    private String challenge;
    @SerializedName("challengeIdentifier")
    @Expose
    private String challengeIdentifier;
    @SerializedName("externalAuthenticationUrl")
    @Expose
    private String externalAuthenticationUrl;
    @SerializedName("allowCredentials")
    @Expose
    private AllowCredentials allowCredentials;

    public List<SupportedCredentialKind> getSupportedCredentialKinds() {
        return supportedCredentialKinds;
    }

    public void setSupportedCredentialKinds(List<SupportedCredentialKind> supportedCredentialKinds) {
        this.supportedCredentialKinds = supportedCredentialKinds;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getChallengeIdentifier() {
        return challengeIdentifier;
    }

    public void setChallengeIdentifier(String challengeIdentifier) {
        this.challengeIdentifier = challengeIdentifier;
    }

    public String getExternalAuthenticationUrl() {
        return externalAuthenticationUrl;
    }

    public void setExternalAuthenticationUrl(String externalAuthenticationUrl) {
        this.externalAuthenticationUrl = externalAuthenticationUrl;
    }

    public AllowCredentials getAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(AllowCredentials allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DfnsChallenge.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("supportedCredentialKinds");
        sb.append('=');
        sb.append(((this.supportedCredentialKinds == null)?"<null>":this.supportedCredentialKinds));
        sb.append(',');
        sb.append("challenge");
        sb.append('=');
        sb.append(((this.challenge == null)?"<null>":this.challenge));
        sb.append(',');
        sb.append("challengeIdentifier");
        sb.append('=');
        sb.append(((this.challengeIdentifier == null)?"<null>":this.challengeIdentifier));
        sb.append(',');
        sb.append("externalAuthenticationUrl");
        sb.append('=');
        sb.append(((this.externalAuthenticationUrl == null)?"<null>":this.externalAuthenticationUrl));
        sb.append(',');
        sb.append("allowCredentials");
        sb.append('=');
        sb.append(((this.allowCredentials == null)?"<null>":this.allowCredentials));
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
        result = ((result* 31)+((this.challenge == null)? 0 :this.challenge.hashCode()));
        result = ((result* 31)+((this.supportedCredentialKinds == null)? 0 :this.supportedCredentialKinds.hashCode()));
        result = ((result* 31)+((this.externalAuthenticationUrl == null)? 0 :this.externalAuthenticationUrl.hashCode()));
        result = ((result* 31)+((this.allowCredentials == null)? 0 :this.allowCredentials.hashCode()));
        result = ((result* 31)+((this.challengeIdentifier == null)? 0 :this.challengeIdentifier.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DfnsChallenge) == false) {
            return false;
        }
        DfnsChallenge rhs = ((DfnsChallenge) other);
        return ((((((this.challenge == rhs.challenge)||((this.challenge!= null)&&this.challenge.equals(rhs.challenge)))&&((this.supportedCredentialKinds == rhs.supportedCredentialKinds)||((this.supportedCredentialKinds!= null)&&this.supportedCredentialKinds.equals(rhs.supportedCredentialKinds))))&&((this.externalAuthenticationUrl == rhs.externalAuthenticationUrl)||((this.externalAuthenticationUrl!= null)&&this.externalAuthenticationUrl.equals(rhs.externalAuthenticationUrl))))&&((this.allowCredentials == rhs.allowCredentials)||((this.allowCredentials!= null)&&this.allowCredentials.equals(rhs.allowCredentials))))&&((this.challengeIdentifier == rhs.challengeIdentifier)||((this.challengeIdentifier!= null)&&this.challengeIdentifier.equals(rhs.challengeIdentifier))));
    }

}
