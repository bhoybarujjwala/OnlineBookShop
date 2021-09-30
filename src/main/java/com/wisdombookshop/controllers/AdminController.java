package com.wisdombookshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wisdombookshop.entities.Book;
import com.wisdombookshop.entities.BookCategory;
import com.wisdombookshop.entities.EmpData;
import com.wisdombookshop.entities.PurchaseDetails;
import com.wisdombookshop.entities.User;
import com.wisdombookshop.models.Information;
import com.wisdombookshop.models.Response;
import com.wisdombookshop.services.BookCategoryService;
import com.wisdombookshop.services.BookService;
import com.wisdombookshop.services.EmpDataService;
import com.wisdombookshop.services.PurchaseDetailsService;
import com.wisdombookshop.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/wisdombookshop/admin")
public class AdminController {
	@Autowired
	private BookCategoryService catserv;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private EmpDataService dataServ;
	
	@Autowired
	private BookService bookserv;
	
	@Autowired
	private PurchaseDetailsService purchaseServ;
	
	
	//delete-customer
	@DeleteMapping("/delete-customer/{userid}")
	public ResponseEntity<?> deletecustomer(@PathVariable("userid") int userid){
		  if(userServ.deleteById(userid))
			return Response.success(true);
		  return Response.error("Error");
	}
	
	//all-executive
	@GetMapping("/executive-list")
	public ResponseEntity<?> getallexecutive(){
		List<Information> infolist = new ArrayList<Information>();
		List<User> userlist = userServ.findByUserRole("executive");
		if(userlist != null){
			for (User user : userlist) {
				if(user !=null) {
				EmpData data = dataServ.findById(user.getUserId());
				if(data !=null) {
				Information info = new Information(user.getUserId(),user.getUserRole(),
						user.getUserName(),user.getUserPhone(),user.getUserEmail(),null,
						user.getUserProfile(),data.getSalary(),data.getLeaves(),data.getManagerId(), data.getCity());
				infolist.add(info);
				}
				}
			}
			return Response.success(infolist);
		}
		return Response.error("Error");	
	}
	
	//add- employee
	@PostMapping("/add-employee")
	public ResponseEntity<?> addemployee(Information info) {
		if(info != null) {
			User user = new User(info.getUserRole(), info.getUserName(), 
					         info.getUserPhone(), info.getUserEmail(), info.getUserPassword());
			userServ.save(user, info.getUserProfile());
			EmpData data = new EmpData(user.getUserId(),info.getSalary(),
					             info.getLeaves(),null, info.getManagerId(), info.getCity());
			dataServ.save(data);
			return Response.success(true);	
		}
		return Response.error("Error");	
	}
	
	//edit-employee
	@PutMapping("/edit-employee/{userId}") //also approve for leave
	public ResponseEntity<?> editemployee(@PathVariable("userId") int userId, EmpData data){
		EmpData olddata = dataServ.findById(userId);
		if(olddata != null){
			olddata.setSalary(data.getSalary());//leave --pending  //leave-null/approved/rejected
			olddata.setLeaves(data.getLeaves());
			//approved//null
			return Response.success(dataServ.save(olddata));
		}
		return Response.error("Error");
	}
	
	@PutMapping("/approve-leave/{leavestatus}/{userId}") //approve for leave
	public ResponseEntity<?> approveleave(@PathVariable("leavestatus") String leavestatus, @PathVariable("userId") int userId){
		EmpData olddata = dataServ.findById(userId);//approved//rejected
		System.out.println(olddata);
		//System.out.println(data);
		if(olddata != null){
			if(leavestatus.equals("approved")) {
				olddata.setLeaves(olddata.getLeaves() + 1);
				System.out.println(olddata.getLeaves());
			}
			olddata.setLeavestatus(leavestatus); //approved
			return Response.success(dataServ.save(olddata));
		}
		return Response.error("Error");
	}
	
	
	//all-employee
	@GetMapping("/employee-list")
	public ResponseEntity<?> getallemployee(){
		List<Information> infolist = new ArrayList<Information>();
		List<User> userlist = userServ.findByUserRole("employee");
		//System.out.println(userlist);
		if(userlist !=null){
			for (User user : userlist) {
				if(user !=null) {
				EmpData data = dataServ.findById(user.getUserId());
				//System.out.println(data);
				if(data !=null) {
				Information info = new Information(user.getUserId(),user.getUserRole(),
						user.getUserName(),user.getUserPhone(),user.getUserEmail(),null,
						user.getUserProfile(),data.getSalary(),data.getLeaves(),data.getManagerId(), data.getCity());
				//System.out.println(data);
				infolist.add(info);
				}
				}
			}
			return Response.success(infolist);
		}
		return Response.error("Error");	
	}
	
	//all-book-list
	@GetMapping("/book-list")
	public ResponseEntity<?> getallbooks(){
		return Response.success(bookserv.findBookAll());
	}

	//id   Cat-name  see-book-list 
	@GetMapping("/books/search")
	public ResponseEntity<?> findBookLikeName(@RequestParam(name="q", required=false) String query) {
		if(query == null)
			query = "";
		System.out.println(query);
		List<Book> list =bookserv.findBookLikeTitle(query);
		System.out.println(list);
		//Stream<BookDTO> result = list.stream().map(book -> BookDTO.fromEntity(book));
		//System.out.println(result);
		return Response.success(list);
	}
	
	// Total Salary Expenditure - Salary of all employee + salary of all executive
	// Total Sale  - Sale for CAT A + Sale for CAT B +......+Sale for CAT Z
	// Total Profit - total sale * 0.2 - Total salary Expenditure 
	
	@GetMapping("/salary-expenditure/{role}")
	public ResponseEntity<?> salaryexpenditure(@PathVariable("role") String role) {
		List<User> userlist = userServ.findByUserRole(role);
		double emp_salary=0;
		for (User user : userlist) {
			int id = user.getUserId();
			EmpData newuser = dataServ.findById(id);
			emp_salary =emp_salary + newuser.getSalary();
		}
		return Response.success(emp_salary);
	}
	
	@GetMapping("/total-sale/{catId}")
	public ResponseEntity<?> salaryexpenditure(@PathVariable("catId") int catId) {
		System.out.println(catId);
		double cat_total=0;
		List<PurchaseDetails> purchaselist = purchaseServ.findAll();
		System.out.println(purchaselist);
		for (PurchaseDetails purchaseDetails : purchaselist) {
			int bid = purchaseDetails.getBookId();
			System.out.println(bid);
			Book newbook = bookserv.findBookById(bid);
			System.out.println(newbook);
			int categoryid = newbook.getBookcategory().getCategoryId();
			if(categoryid == catId) {
				cat_total= cat_total + purchaseDetails.getTotal();
			}
		}
		
		return Response.success(cat_total);
	}	
	
	//method for total profit
		@GetMapping("/total-profit")
		public ResponseEntity<?> getProfit() {
		List<BookCategory> catList= catserv.findAllCategoryId();
	    double allCatSale=0.0;
		double allEmpExp=0.0;
		double allDelExeExp=0.0;
		double totalProfit = 0.0;
		  for(BookCategory bookCat : catList)
			{
				int catId = bookCat.getCategoryId();
				List<PurchaseDetails> purchaselist = purchaseServ.findAll();
				for (PurchaseDetails purchaseDetails : purchaselist) {
					int bid = purchaseDetails.getBookId();
					Book newbook = bookserv.findBookById(bid);
					int categoryid = newbook.getBookcategory().getCategoryId();
					if(categoryid == catId) {
						allCatSale= allCatSale + purchaseDetails.getTotal();}
				}
			}	
		  List<User> useremplist = userServ.findByUserRole("employee");
			for (User user : useremplist) {
				int id1 = user.getUserId();
				EmpData newuser1 = dataServ.findById(id1);
				allEmpExp =allEmpExp + newuser1.getSalary();
			}
			List<User> userexelist = userServ.findByUserRole("executive");
			for (User user : userexelist) {
				int id2 = user.getUserId();
				EmpData newuser2 = dataServ.findById(id2);
				allDelExeExp =allDelExeExp + newuser2.getSalary();
			}
			
		  totalProfit = (allCatSale * 0.2) - (allEmpExp+allDelExeExp);
		  return Response.success(totalProfit);
	}
		
		//all employee who are applying for leaves
		@GetMapping("/employee-list/leaves")
		public ResponseEntity<?> getempleaveslist(){
			List<Information> infolist = new ArrayList<Information>();
			List<User> userlist = userServ.findByUserRole("employee");
			//System.out.println(userlist);
			if(userlist !=null){
				for (User user : userlist) {
					if(user !=null) {
					EmpData data = dataServ.findById(user.getUserId());
					//System.out.println(data);
					if(data.getLeavestatus() != null ) {
						if(data.getLeavestatus().equals("pending")) {
					Information info = new Information(user.getUserId(),user.getUserRole(),
							user.getUserName(),user.getUserPhone(),user.getUserEmail(),null,
							user.getUserProfile(),data.getSalary(),data.getLeaves(),data.getManagerId(), data.getCity());
					//System.out.println(data);
					infolist.add(info);
					}
					}
					}
				}
				return Response.success(infolist);
			}
			return Response.error("Error");	
		}
		
	
}
