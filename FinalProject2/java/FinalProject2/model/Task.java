package FinalProject2.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tbl_task")
public class Task implements Serializable {
	
	@Id
	@Column(name = "task_id")
	@NotNull
	private String task_id;
	
	@Column(name = "task_name")
	@NotNull
	private String task_name;
	
	@Column(name = "task_division_id")
	@NotNull
	private int task_division_id;
	
	@Column(name = "budget")
	@NotNull
	private int budget;
	
	@Column(name = "detail")
	@NotNull
	private String detail;
	
	@Column(name = "employee_id")
	@NotNull
	private String employee_id;
	
	@Column(name = "due_date")
	@NotNull
	private LocalDate due_date;
	
	@Column(name = "end_flg")
	@NotNull
	private boolean end_flg;
	
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
