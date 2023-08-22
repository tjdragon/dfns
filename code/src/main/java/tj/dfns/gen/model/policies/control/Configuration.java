
package tj.dfns.gen.model.policies.control;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Configuration {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("approverUsernames")
    @Expose
    private List<String> approverUsernames = new ArrayList<String>();
    @SerializedName("timeoutInMinutes")
    @Expose
    private Integer timeoutInMinutes;
    @SerializedName("numApprovals")
    @Expose
    private Integer numApprovals;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<String> getApproverUsernames() {
        return approverUsernames;
    }

    public void setApproverUsernames(List<String> approverUsernames) {
        this.approverUsernames = approverUsernames;
    }

    public Integer getTimeoutInMinutes() {
        return timeoutInMinutes;
    }

    public void setTimeoutInMinutes(Integer timeoutInMinutes) {
        this.timeoutInMinutes = timeoutInMinutes;
    }

    public Integer getNumApprovals() {
        return numApprovals;
    }

    public void setNumApprovals(Integer numApprovals) {
        this.numApprovals = numApprovals;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Configuration.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("kind");
        sb.append('=');
        sb.append(((this.kind == null)?"<null>":this.kind));
        sb.append(',');
        sb.append("approverUsernames");
        sb.append('=');
        sb.append(((this.approverUsernames == null)?"<null>":this.approverUsernames));
        sb.append(',');
        sb.append("timeoutInMinutes");
        sb.append('=');
        sb.append(((this.timeoutInMinutes == null)?"<null>":this.timeoutInMinutes));
        sb.append(',');
        sb.append("numApprovals");
        sb.append('=');
        sb.append(((this.numApprovals == null)?"<null>":this.numApprovals));
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
        result = ((result* 31)+((this.timeoutInMinutes == null)? 0 :this.timeoutInMinutes.hashCode()));
        result = ((result* 31)+((this.approverUsernames == null)? 0 :this.approverUsernames.hashCode()));
        result = ((result* 31)+((this.numApprovals == null)? 0 :this.numApprovals.hashCode()));
        result = ((result* 31)+((this.kind == null)? 0 :this.kind.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Configuration) == false) {
            return false;
        }
        Configuration rhs = ((Configuration) other);
        return (((((this.timeoutInMinutes == rhs.timeoutInMinutes)||((this.timeoutInMinutes!= null)&&this.timeoutInMinutes.equals(rhs.timeoutInMinutes)))&&((this.approverUsernames == rhs.approverUsernames)||((this.approverUsernames!= null)&&this.approverUsernames.equals(rhs.approverUsernames))))&&((this.numApprovals == rhs.numApprovals)||((this.numApprovals!= null)&&this.numApprovals.equals(rhs.numApprovals))))&&((this.kind == rhs.kind)||((this.kind!= null)&&this.kind.equals(rhs.kind))));
    }

}
