package main.java.core.constants;

public class RegexConstants {
	//TODO: Could be exploited for injection
	public static final String GOOGLESERVICEACCOUNTACTIVATEDSUCCESS = ".*Activated service account credentials for:.*";
	public static final String GOOGLESERVICEACCOUNTACTIVATEDFAILURE = ".*ERROR:.*Missing required argument.*";

	public static final String GOOGLESETACCOUNTSUCCESS = ".*Updated property.*core.*account.*";

	public static final String GOOGLESTARTSTOPSUCCESS = ".*Updated.*";
	public static final String GOOGLESTARTSTOPFAILURE = ".*ERROR:.*was not found.*";
}
