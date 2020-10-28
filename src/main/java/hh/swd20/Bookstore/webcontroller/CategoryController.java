package hh.swd20.Bookstore.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

	@Autowired
	private CategoryRepository cRepository;

	@RequestMapping("/categorylist")
	public String bookList(Model model) {
		model.addAttribute("categories", cRepository.findAll());
		return "categorylist";
	}

	@RequestMapping(value = "/addcategory")
	public String addBook(Model model) {
		model.addAttribute("category", new Category());
		return "newcategory";
	}

	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
	public String save(Category category) {
		cRepository.save(category);
		return "redirect:categorylist";
	}
}