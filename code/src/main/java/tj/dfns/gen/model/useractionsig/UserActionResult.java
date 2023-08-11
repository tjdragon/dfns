
package tj.dfns.gen.model.useractionsig;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class UserActionResult {

    @SerializedName("userAction")
    @Expose
    private String userAction;

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserActionResult.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userAction");
        sb.append('=');
        sb.append(((this.userAction == null)?"<null>":this.userAction));
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
        result = ((result* 31)+((this.userAction == null)? 0 :this.userAction.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserActionResult) == false) {
            return false;
        }
        UserActionResult rhs = ((UserActionResult) other);
        return ((this.userAction == rhs.userAction)||((this.userAction!= null)&&this.userAction.equals(rhs.userAction)));
    }

}
