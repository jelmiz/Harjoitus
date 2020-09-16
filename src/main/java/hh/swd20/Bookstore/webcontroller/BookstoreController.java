package hh.swd20.Bookstore.webcontroller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.Bookstore.domain.Book;
@Controller
public class BookstoreController {
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getBooks(Model model) {
		return "Hello";
	}
	}
