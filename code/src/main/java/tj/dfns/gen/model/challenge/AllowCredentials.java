
package tj.dfns.gen.model.challenge;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("jsonschema2pojo")
public class AllowCredentials {

    @SerializedName("webauthn")
    @Expose
    private List<Object> webauthn = new ArrayList<Object>();
    @SerializedName("key")
    @Expose
    private List<Key> key = new ArrayList<Key>();

    public List<Object> getWebauthn() {
        return webauthn;
    }

    public void setWebauthn(List<Object> webauthn) {
        this.webauthn = webauthn;
    }

    public List<Key> getKey() {
        return key;
    }

    public void setKey(List<Key> key) {
        this.key = key;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AllowCredentials.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("webauthn");
        sb.append('=');
        sb.append(((this.webauthn == null)?"<null>":this.webauthn));
        sb.append(',');
        sb.append("key");
        sb.append('=');
        sb.append(((this.key == null)?"<null>":this.key));
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
        result = ((result* 31)+((this.webauthn == null)? 0 :this.webauthn.hashCode()));
        result = ((result* 31)+((this.key == null)? 0 :this.key.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AllowCredentials) == false) {
            return false;
        }
        AllowCredentials rhs = ((AllowCredentials) other);
        return (((this.webauthn == rhs.webauthn)||((this.webauthn!= null)&&this.webauthn.equals(rhs.webauthn)))&&((this.key == rhs.key)||((this.key!= null)&&this.key.equals(rhs.key))));
    }

}
