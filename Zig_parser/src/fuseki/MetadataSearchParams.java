package fuseki;

public class MetadataSearchParams {
	private String property;
	private String value;
	private String operator;
	
	public MetadataSearchParams(String property, String value, String operator) {
		this.property = property;
		this.value = value;
		this.operator = operator;
	}
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
}
