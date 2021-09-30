package com.wisdombookshop.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
import org.springframework.web.multipart.MultipartFile;

import com.wisdombookshop.entities.Address;
import com.wisdombookshop.entities.Book;
import com.wisdombookshop.entities.BookCategory;
import com.wisdombookshop.entities.EmpData;
import com.wisdombookshop.entities.OrderDetails;
import com.wisdombookshop.entities.User;
import com.wisdombookshop.models.BookDTO;
import com.wisdombookshop.models.Information;
import com.wisdombookshop.models.Response;
import com.wisdombookshop.models.UserDTO;
import com.wisdombookshop.services.AddressService;
import com.wisdombookshop.services.BookCategoryService;
import com.wisdombookshop.services.BookService;
import com.wisdombookshop.services.EmpDataService;
import com.wisdombookshop.services.OrderDetailsService;
import com.wisdombookshop.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("/wisdombookshop/employee")
public class EmployeeController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private EmpDataService dataServ;
	
	@Autowired
	private BookService bookserv;
	
	@Autowired
	private BookCategoryService catserv;
	
	@Autowired
	private OrderDetailsService orderserv;
	
	@Autowired
	private AddressService addServ;
	
	//Add New book of CategoryId
	@PostMapping("/add-book/{categoryId}")
	public ResponseEntity<?> addBook(@PathVariable("categoryId") int catId, BookDTO bookdto){
		Book book = BookDTO.toEntity(bookdto);
		BookCategory category = catserv.findByCategoryId(catId);
		if(category!= null) {
			book.setBookcategory(category);
			return Response.success(bookserv.save(book, bookdto.getThumbnail()));
		}
		return Response.error("Selected Category Not Found!!!");
		
	}
	
	//Edit-Book of a bookId
	@PutMapping("/edit-book/{newbid}")
	public ResponseEntity<?> editBook(@PathVariable("newbid") int newbid, BookDTO bookdto){
		Book book = BookDTO.toEntity(bookdto);
		Book oldbook =bookserv.findBookById(newbid);
		if(oldbook!= null) {
			oldbook.setBookPrice(book.getBookPrice());
			oldbook.setBookDetails(book.getBookDetails());
			oldbook.setInventory(book.getInventory());
			return Response.success(bookserv.save(oldbook, bookdto.getThumbnail()));
		}else
			return Response.error("Selected Book Not Found!!!");
		
	}
	
	//Add New BookCategory
	@PostMapping("/add-bookcat")
	public ResponseEntity<?> addBookcat(BookCategory bookcat){
		System.out.println(bookcat);
		return Response.success(catserv.save(bookcat));
	}
	
	//Get All categories
	@GetMapping("/fetch-category")
	public ResponseEntity<?> fetchCategory(){
		List<BookCategory> list = catserv.findAllCategoryId();
		if(list != null)
			return Response.success(list);
		return Response.error("Error");
	}

	//Delete Book of bookId
	@DeleteMapping("/delete-book/{bookid}")
	public ResponseEntity<?> deleteBookc(@PathVariable("bookid") int bookid){
			return Response.success(bookserv.deleteByid(bookid));
		//return Response.error("Book doesn't exists");
	}
	
	//Get All books
	@GetMapping("/books")
	public ResponseEntity<?> getallbooks(){
		return Response.success(bookserv.findBookAll());
	}
	
	//Search Book by key-word
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
	
	//Get books of one Category
	@GetMapping("/books/{categoryId}")
	public ResponseEntity<?> getbooksbycategory(@PathVariable("categoryId") int catId){
		System.out.println(catId);
		return Response.success(bookserv.getbooksbycatid(catId));
	}
	
	//Add new Executive By employee
	@PostMapping("/add-executive")
	public ResponseEntity<?> addexecutive(Information info) {
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
	
	//Edit Executive of userId
	@PutMapping("/edit-executive/{userId}") 
	public ResponseEntity<?> editexecutive(@PathVariable("userId") int userId, EmpData data){
		EmpData olddata = dataServ.findById(userId);
		if(olddata != null){
			olddata.setSalary(data.getSalary());
			//olddata.setLeaves(data.getLeaves());
			return Response.success(dataServ.save(olddata));
		}
		return Response.error("Error");
	}
	
	//Delete Executive of userId
	@DeleteMapping("/delete-executive/{userid}")
	public ResponseEntity<?> deleteexecutive(@PathVariable("userid") int userid){
		   dataServ.deleteByid(userid);
		   userServ.deleteById(userid);
			return Response.success(true);
	}
	
	//Get all-executive of employee
	@GetMapping("/executive-list/{mgrId}")
	public ResponseEntity<?> getallexecutive(@PathVariable("mgrId") int mgrId){
		List<Information> infolist = new ArrayList<Information>();
		List<User> userlist = userServ.findByUserRole("executive");
		if(userlist != null){
			for (User user : userlist) {
				if(user !=null) {
				EmpData data = dataServ.findById(user.getUserId());
				if(data !=null && data.getManagerId() == mgrId) {
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
	//emp ---> //1. Name  	Delete   Edit  ApproveLeave(leaves-number) RejectLeave
	
	//Orderlist for a employee
	@GetMapping("/orders-list/{empid}")
	public ResponseEntity<?> getallorders(@PathVariable("empid") int empid){
		EmpData empdata = dataServ.findById(empid);
		List<OrderDetails> orderlist = orderserv.findOrdersforCity(empdata.getCity());
		return Response.success(orderlist);
	}
	
	//Assign Order To executive
	@PostMapping("/assign-order/{orderId}")
	public ResponseEntity<?> assignrder(@PathVariable("orderId") int orderId, OrderDetails newdetails){
		OrderDetails oldorder = orderserv.findById(orderId);
		oldorder.setDelExeId(newdetails.getDelExeId());
		return Response.success(orderserv.save(oldorder));
	}
	
	//Apply for leaves to Admin
	@PostMapping("/leave-apply")
	public ResponseEntity<?> applyleaves(EmpData data ){
		EmpData executive = dataServ.findById(data.getUserId());
		if(executive != null) {
			executive.setLeavestatus(data.getLeavestatus());
			return Response.success(dataServ.save(executive));
		}
		return Response.error("Error");
	}
		
	//leave-status
	@GetMapping("/leave-status/{userid}")
	public ResponseEntity<?> leavestatus(@PathVariable("userid") int userid){
		EmpData data = dataServ.findById(userid);
		if(data != null) {
			return Response.success(data.getLeavestatus());
		}
		return Response.error("Error");
	}
	
	//Approve leaves of executives
	@PutMapping("/approve-leave/{userId}") //approve for leave
	public ResponseEntity<?> approveleave(@PathVariable("userId") int userId, EmpData data){
		
		EmpData olddata = dataServ.findById(userId);//approved//rejected
		/*LocalDate date = LocalDate.now();
		if(date.getDayOfMonth() == 1 ) {
			olddata.setLeaves(0);
		}*/
		System.out.println(olddata);
		System.out.println(data);
		if(olddata != null){
			olddata.setLeavestatus(data.getLeavestatus()); //approved
			if(data.getLeavestatus().equals("approved")) {
				olddata.setLeaves(olddata.getLeaves() + 1);
				System.out.println(olddata.getLeaves());
			}
			
			return Response.success(dataServ.save(olddata));
		}
		return Response.error("Error");
	}
	
	//Get Customer Address
	@GetMapping("/get-address/{userid}")
	public ResponseEntity<?> getaddress(@PathVariable("userid") int userid){
		Address address = addServ.findById(userid);
		return Response.success(address);
	}
	
	//Get emp data 
	@GetMapping("/get-empdata/{userid}")
	public ResponseEntity<?> getempdata(@PathVariable("userid") int userid){
		EmpData data = dataServ.findById(userid);
		return Response.success(data);
	}
	
	//get all leave applied executives
	@GetMapping("/leaveapplied-list/{mgrId}")
	public ResponseEntity<?> getleaveapplied(@PathVariable("mgrId") int mgrId){
		List<Information> infolist = new ArrayList<Information>();
		List<User> userlist = userServ.findByUserRole("executive");
		if(userlist != null){
			for (User user : userlist) {
				if(user !=null) {
					EmpData data = dataServ.findById(user.getUserId());
				if(data != null && data.getLeavestatus() != null ) {
					
					if( data.getManagerId() == mgrId && (data.getLeavestatus().equals("pending"))) {
					Information info = new Information(user.getUserId(),user.getUserRole(),
							user.getUserName(),user.getUserPhone(),user.getUserEmail(),null,
							user.getUserProfile(),data.getSalary(),data.getLeaves(),data.getManagerId(), data.getCity());
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

//getallorders ---> getordersbyCity(empcity)
// ostatus city
//userId  orderid  odate  (delivered/undelivered) ostatus del-date del-exe-id  Assign-to-executie -->5
