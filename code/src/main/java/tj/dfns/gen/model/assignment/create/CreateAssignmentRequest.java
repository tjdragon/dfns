
package tj.dfns.gen.model.assignment.create;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CreateAssignmentRequest {

    @SerializedName("permissionId")
    @Expose
    private String permissionId;
    @SerializedName("identityId")
    @Expose
    private String identityId;
    @SerializedName("identityKind")
    @Expose
    private String identityKind;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getIdentityKind() {
        return identityKind;
    }

    public void setIdentityKind(String identityKind) {
        this.identityKind = identityKind;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CreateAssignmentRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("permissionId");
        sb.append('=');
        sb.append(((this.permissionId == null)?"<null>":this.permissionId));
        sb.append(',');
        sb.append("identityId");
        sb.append('=');
        sb.append(((this.identityId == null)?"<null>":this.identityId));
        sb.append(',');
        sb.append("identityKind");
        sb.append('=');
        sb.append(((this.identityKind == null)?"<null>":this.identityKind));
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
        result = ((result* 31)+((this.permissionId == null)? 0 :this.permissionId.hashCode()));
        result = ((result* 31)+((this.identityKind == null)? 0 :this.identityKind.hashCode()));
        result = ((result* 31)+((this.identityId == null)? 0 :this.identityId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CreateAssignmentRequest) == false) {
            return false;
        }
        CreateAssignmentRequest rhs = ((CreateAssignmentRequest) other);
        return ((((this.permissionId == rhs.permissionId)||((this.permissionId!= null)&&this.permissionId.equals(rhs.permissionId)))&&((this.identityKind == rhs.identityKind)||((this.identityKind!= null)&&this.identityKind.equals(rhs.identityKind))))&&((this.identityId == rhs.identityId)||((this.identityId!= null)&&this.identityId.equals(rhs.identityId))));
    }

}
