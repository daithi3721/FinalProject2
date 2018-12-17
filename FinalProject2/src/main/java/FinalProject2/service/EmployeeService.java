package FinalProject2.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import FinalProject2.model.Employee;
import FinalProject2.model.EmployeeSearch;
import FinalProject2.model.UserAccount;
import FinalProject2.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	
	HttpSession session;
	
	@Autowired
	EmployeeRepository repository;
	
	//20行ごとにページングするように設定
	private static final int PAGE_SIZE=20;
	
	private static final String 
	
	//pagination実装のためにListからPageに戻り値の型を変更。キャストも追加
	public Page<Employee> findAll(int page) {

		return repository.findAll(PageRequest.of(page<=0?0:page, PAGE_SIZE));
		
	}
	
	//検索条件有ページネーション実装の為にEmployeeのListを取得
	public List<Employee> findAll() {

		return repository.findAll();
		
	}
	
	//検索条件を元に
	public Page<Employee> findBySearch(int page, List<Employee> employees_base, EmployeeSearch employeeSearch) {
		
		//検索条件を取得
		String employee_name = employeeSearch.getEmployee_name();
		String employeeId_start = employeeSearch.getEmployeeId_start();
		String employeeId_to = employeeSearch.getEmployeeId_to();
		
		//従業員IDのインプット欄の１つ目が空欄だったら
		if(employeeId_start.equals("")) {
			employeeId_start = employees_base.get(0).getEmployee_id();
		}
		//従業員IDのインプット欄の２つ目が空欄だったら
		if(employeeId_to.equals("")) {
			employeeId_to = employees_base.get(employees_base.size() - 1).getEmployee_id();
		}
		
		char sex1 = '0';
		char sex2 = '0';
		
		switch(employeeSearch.getSex()) {
		case "": //未選択の場合
			sex1 = '0';
			sex2 = '1';
			break;
		case "0": //男性の場合
			sex1 = '0';
			sex2 = '0';
			break;
		case "1": //女性の場合
			sex1 = '1';
			sex2 = '1';
			break;
		}

		LocalDate now = LocalDate.now();
		LocalDate birthday_start = null;
		LocalDate birthday_to = null;
		
		switch(employeeSearch.getAge()) {
		case "": //未選択の場合
			birthday_start = now.minusYears(100);
			birthday_to = now.minusYears(1);
			break;
		case "10代":
			birthday_start = now.minusYears(19);
			birthday_to = now.minusYears(10);
			break;
		case "20代":
			birthday_start = now.minusYears(29);
			birthday_to = now.minusYears(20);
			break;
		case "30代":
			birthday_start = now.minusYears(39);
			birthday_to = now.minusYears(30);
			break;
		case "40代":
			birthday_start = now.minusYears(49);
			birthday_to = now.minusYears(40);
			break;
		case "50代":
			birthday_start = now.minusYears(59);
			birthday_to = now.minusYears(50);
			break;
		}
		
		String department_name = employeeSearch.getDepartment_name();
		String position_name = employeeSearch.getPosition_name();
		
		if(department_name.equals("") && position_name.equals("")) {
			
			return repository.findBySearch(employeeId_start, employeeId_to, employee_name, 
					birthday_start, birthday_to, sex1, sex2, 
					PageRequest.of(page<=0?0:page, PAGE_SIZE));
			
		} else {
			
			return repository.findBySearch(employeeId_start, employeeId_to, employee_name, 
					birthday_start, birthday_to, department_name, position_name, sex1, sex2, 
					PageRequest.of(page<=0?0:page, PAGE_SIZE));
			
		}

		
	}
	
	public Optional<Employee> findOne(String employee_id) {
        return repository.findById(employee_id);
    }
	
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Transactional
    public void delete(String employee_id) {
        repository.deleteById(employee_id);
    }
    
    public String makeNewEmployeeId() {
    	
			List<Employee> employees = findAll();
	
			int i = 0;
			for(Employee employee: employees) {
					if(Integer.parseInt(employee.getEmployee_id()) > i) {
							i = Integer.parseInt(employee.getEmployee_id());
					}
			}
			String NewEmployeeId = String.format("%4s", String.valueOf(i + 1)).replace(" ", "0");
			
	    	return NewEmployeeId;
    }

	public int getActiveEmployeesNumber() {
		
		int activeEmployeesNumber =repository.findActiveEmployeeNumber();
		
		return activeEmployeesNumber;
		
	}

	public List<String> getPrefecturesList() {
		
		List<String> prefecturesList = Arrays.asList(
				"北海道", "青森県", "岩手県", "宮城県", "秋田県", "山形県", "福島県", "茨城県", "栃木県", 
				"群馬県", "埼玉県", "千葉県", "東京都", "新潟県", "富山県", "石川県", "福井県", "山梨県",
				"長野県", "静岡県", "愛知県", "三重県", "滋賀県", "京都府", "大阪府", "兵庫県", "奈良県",
				"和歌山県", "鳥取県", "島根県", "岡山県", "山口県", "徳島県",  "香川県", "愛媛県", "高知県",
				"福岡県", "佐賀県", "長崎県", "熊本県", "大分県", "宮崎県", "鹿児島県", "沖縄県"
				);

		return prefecturesList;
		
	}

	public void saveFirst(Employee new_employee) {
		
		LocalDate localdate_now = LocalDate.now();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		UserAccount  makeUserAccount = (UserAccount) session.getAttribute("user");
		String makeUserName  = makeUserAccount.getUsername();
		
		new_employee.setMake_date(timestamp);
		new_employee.setMake_user(makeUserName);
		new_employee.setPassword("123456");
		new_employee.setPassword_update(localdate_now);
		new_employee.setUpdate_date(timestamp);
		new_employee.setUpdate_user(makeUserName);
		
		repository.save(new_employee);
		
	}
			
		
		
}
