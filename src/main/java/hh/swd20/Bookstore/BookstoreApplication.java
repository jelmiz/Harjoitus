package hh.swd20.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import hh.swd20.Bookstore.domain.Book;
import hh.swd20.Bookstore.domain.BookRepository;
import hh.swd20.Bookstore.domain.Category;
import hh.swd20.Bookstore.domain.CategoryRepository;
import hh.swd20.Bookstore.domain.User;
import hh.swd20.Bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder
	application) {
	return application.sources(BookstoreApplication.class);
	}
	
     private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner CLR(BookRepository bookRepository, CategoryRepository cRepository, UserRepository uRepository) {
        return (args) -> {

        	Category c1 = new Category("Scifi");
            Category c2 = new Category("Fantasy");
            Category c3 = new Category("Horror");
    
            cRepository.save(c1);
            cRepository.save(c2);
            cRepository.save(c3);
            

            Book Book1 = new Book("Tähti sota", "Markku", 2020, "123452424",20.00, c2);
            Book Book2 = new Book("Metsän salat", "Kettu", 2015, "11223352",21.00, c3);


            bookRepository.save(Book1);
            bookRepository.save(Book2);


        	User user1 = new User("user", "$2y$10$Hf9NL8Ieu.Tw99PfPoD14.s2uBHJjDTnttr9YlSwMBPcaYJZTmTVu", "ROLE_USER");
        	User user2 = new User("admin", "$2y$10$LX8vykzELW7F0BTjkylSuuh1EO0m896amOGXaE.vsQrSLMBlLE9bS", "ROLE_ADMIN");
           
        	uRepository.save(user1);
        	uRepository.save(user2);
        	

            for (Category category : cRepository.findAll()) {
                log.info(category.toString());
            }
            for (Book book : bookRepository.findAll()) {
                log.info(book.toString());
            }
        };
    }
}