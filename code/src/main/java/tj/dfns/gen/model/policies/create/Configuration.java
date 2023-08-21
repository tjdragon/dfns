
package tj.dfns.gen.model.policies.create;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Configuration {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("limit")
    @Expose
    private String limit;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("assetSymbol")
    @Expose
    private String assetSymbol;
    @SerializedName("shouldIgnoreAssetsWithoutMarketValue")
    @Expose
    private Boolean shouldIgnoreAssetsWithoutMarketValue;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAssetSymbol() {
        return assetSymbol;
    }

    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }

    public Boolean getShouldIgnoreAssetsWithoutMarketValue() {
        return shouldIgnoreAssetsWithoutMarketValue;
    }

    public void setShouldIgnoreAssetsWithoutMarketValue(Boolean shouldIgnoreAssetsWithoutMarketValue) {
        this.shouldIgnoreAssetsWithoutMarketValue = shouldIgnoreAssetsWithoutMarketValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Configuration.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("kind");
        sb.append('=');
        sb.append(((this.kind == null)?"<null>":this.kind));
        sb.append(',');
        sb.append("limit");
        sb.append('=');
        sb.append(((this.limit == null)?"<null>":this.limit));
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("assetSymbol");
        sb.append('=');
        sb.append(((this.assetSymbol == null)?"<null>":this.assetSymbol));
        sb.append(',');
        sb.append("shouldIgnoreAssetsWithoutMarketValue");
        sb.append('=');
        sb.append(((this.shouldIgnoreAssetsWithoutMarketValue == null)?"<null>":this.shouldIgnoreAssetsWithoutMarketValue));
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
        result = ((result* 31)+((this.limit == null)? 0 :this.limit.hashCode()));
        result = ((result* 31)+((this.currency == null)? 0 :this.currency.hashCode()));
        result = ((result* 31)+((this.shouldIgnoreAssetsWithoutMarketValue == null)? 0 :this.shouldIgnoreAssetsWithoutMarketValue.hashCode()));
        result = ((result* 31)+((this.assetSymbol == null)? 0 :this.assetSymbol.hashCode()));
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
        return ((((((this.limit == rhs.limit)||((this.limit!= null)&&this.limit.equals(rhs.limit)))&&((this.currency == rhs.currency)||((this.currency!= null)&&this.currency.equals(rhs.currency))))&&((this.shouldIgnoreAssetsWithoutMarketValue == rhs.shouldIgnoreAssetsWithoutMarketValue)||((this.shouldIgnoreAssetsWithoutMarketValue!= null)&&this.shouldIgnoreAssetsWithoutMarketValue.equals(rhs.shouldIgnoreAssetsWithoutMarketValue))))&&((this.assetSymbol == rhs.assetSymbol)||((this.assetSymbol!= null)&&this.assetSymbol.equals(rhs.assetSymbol))))&&((this.kind == rhs.kind)||((this.kind!= null)&&this.kind.equals(rhs.kind))));
    }

}
