package hh.swd20.Bookstore.webcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.CategoryRepository;

@CrossOrigin
@Controller
public class BookstoreController {

	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository cRepository;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}

	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}

	@RequestMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", cRepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		return "editbook";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	@RequestMapping(value="/login")
    public String login() {
        return "login";
    }
}
