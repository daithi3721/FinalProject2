package FinalProject2.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;;

public class UtilityMethod {
	
	public Timestamp getTimestamp() {
		Timestamp ts = new Timestamp((new java.util.Date()).getTime());
		return ts;
	}
}
