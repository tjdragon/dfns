
package tj.dfns.gen.model.policies.neo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class NewPolicy {

    @SerializedName("activityKind")
    @Expose
    private String activityKind;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ruleIds")
    @Expose
    private List<String> ruleIds = new ArrayList<String>();
    @SerializedName("controlIds")
    @Expose
    private List<String> controlIds = new ArrayList<String>();
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("filter")
    @Expose
    private Filter filter;

    public String getActivityKind() {
        return activityKind;
    }

    public void setActivityKind(String activityKind) {
        this.activityKind = activityKind;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRuleIds() {
        return ruleIds;
    }

    public void setRuleIds(List<String> ruleIds) {
        this.ruleIds = ruleIds;
    }

    public List<String> getControlIds() {
        return controlIds;
    }

    public void setControlIds(List<String> controlIds) {
        this.controlIds = controlIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NewPolicy.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("activityKind");
        sb.append('=');
        sb.append(((this.activityKind == null)?"<null>":this.activityKind));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("ruleIds");
        sb.append('=');
        sb.append(((this.ruleIds == null)?"<null>":this.ruleIds));
        sb.append(',');
        sb.append("controlIds");
        sb.append('=');
        sb.append(((this.controlIds == null)?"<null>":this.controlIds));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null)?"<null>":this.status));
        sb.append(',');
        sb.append("filter");
        sb.append('=');
        sb.append(((this.filter == null)?"<null>":this.filter));
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
        result = ((result* 31)+((this.filter == null)? 0 :this.filter.hashCode()));
        result = ((result* 31)+((this.activityKind == null)? 0 :this.activityKind.hashCode()));
        result = ((result* 31)+((this.ruleIds == null)? 0 :this.ruleIds.hashCode()));
        result = ((result* 31)+((this.controlIds == null)? 0 :this.controlIds.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.status == null)? 0 :this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NewPolicy) == false) {
            return false;
        }
        NewPolicy rhs = ((NewPolicy) other);
        return ((((((((this.filter == rhs.filter)||((this.filter!= null)&&this.filter.equals(rhs.filter)))&&((this.activityKind == rhs.activityKind)||((this.activityKind!= null)&&this.activityKind.equals(rhs.activityKind))))&&((this.ruleIds == rhs.ruleIds)||((this.ruleIds!= null)&&this.ruleIds.equals(rhs.ruleIds))))&&((this.controlIds == rhs.controlIds)||((this.controlIds!= null)&&this.controlIds.equals(rhs.controlIds))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.status == rhs.status)||((this.status!= null)&&this.status.equals(rhs.status))));
    }

}
