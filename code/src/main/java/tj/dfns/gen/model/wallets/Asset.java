
package tj.dfns.gen.model.wallets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class Asset {

    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("decimals")
    @Expose
    private Integer decimals;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("contract")
    @Expose
    private String contract;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getDecimals() {
        return decimals;
    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Asset.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("symbol");
        sb.append('=');
        sb.append(((this.symbol == null)?"<null>":this.symbol));
        sb.append(',');
        sb.append("decimals");
        sb.append('=');
        sb.append(((this.decimals == null)?"<null>":this.decimals));
        sb.append(',');
        sb.append("balance");
        sb.append('=');
        sb.append(((this.balance == null)?"<null>":this.balance));
        sb.append(',');
        sb.append("contract");
        sb.append('=');
        sb.append(((this.contract == null)?"<null>":this.contract));
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
        result = ((result* 31)+((this.symbol == null)? 0 :this.symbol.hashCode()));
        result = ((result* 31)+((this.balance == null)? 0 :this.balance.hashCode()));
        result = ((result* 31)+((this.decimals == null)? 0 :this.decimals.hashCode()));
        result = ((result* 31)+((this.contract == null)? 0 :this.contract.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Asset) == false) {
            return false;
        }
        Asset rhs = ((Asset) other);
        return (((((this.symbol == rhs.symbol)||((this.symbol!= null)&&this.symbol.equals(rhs.symbol)))&&((this.balance == rhs.balance)||((this.balance!= null)&&this.balance.equals(rhs.balance))))&&((this.decimals == rhs.decimals)||((this.decimals!= null)&&this.decimals.equals(rhs.decimals))))&&((this.contract == rhs.contract)||((this.contract!= null)&&this.contract.equals(rhs.contract))));
    }

}
