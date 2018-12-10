package FinalProject2.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@Entity
//@Table(name="tbl_employment_info")
//Todo: moneyカラムをhibernateでマッピング出来ない。intに変更したほうがいいかも。morishita
public class EmploymentInfo implements Serializable {
	
	@Id
	@Column(name = "employee_id")
	@NotNull
	private String employee_id;
	
	@Column(name = "department_id")
	@NotNull
	private String department_id;
	
	@Column(name = "position_id")
	@NotNull
	private int position_id;
	
	@Column(name = "norma")
	@NotNull
	private BigDecimal norma;
	
	@Column(name = "basic_salary", columnDefinition="decimal")
	@NotNull
	private  BigDecimal basic_salary;
	
	@Column(name = "make_date")
	@NotNull
	private Timestamp make_date;
	
	@Column(name = "make_user")
	@NotNull
	private String make_user;
	
	@Column(name = "update_date")
	@NotNull
	private Timestamp update_date;
	
	@Column(name = "update_user")
	@NotNull
	private String update_user;
	
	

	
	
	
	

}
