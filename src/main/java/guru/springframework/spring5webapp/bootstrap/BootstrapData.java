package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher penguin = new Publisher("Penguin", "Penguin Way 1", "Penguin City", "12345");
        publisherRepository.save(penguin);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "12345");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(penguin);
        penguin.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(penguin);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development", "67890");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(penguin);
        penguin.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(penguin);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Authors: " + authorRepository.count());
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("First Author-ID: " + eric.getId());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of books for 1st publisher: " + penguin.getBooks().size());

    }
}
