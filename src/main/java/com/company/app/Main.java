package com.company.app;

import com.company.dao.AuthorDao;
import com.company.dao.BookDao;
import com.company.model.Author;
import com.company.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static AuthorDao authorDao = new AuthorDao();
    static BookDao bookDao = new BookDao();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("Press 1 to add Author");
            System.out.println("Press 2 to view books list");
            System.out.println("Press 3 to assign book to author ");

            switch (scanner.nextInt()) {

                case 1:
                    System.out.println("Please input author's name,surname");
                    System.out.println("after input author's book tittle and price");
                    Author author = new Author();
                    author.setName(scanner.next());
                    author.setSurname(scanner.next());
                    Book book = new Book();
                    book.setTittle(scanner.next());
                    book.setPrice(scanner.nextInt());
                    List<Book> authorBooks = new ArrayList<>();
                    authorBooks.add(book);
                    author.setBooks(authorBooks);
                    authorDao.addAuthor(author);
                    break;
                case 2:
                    for (Book b : bookDao.viewAllBooks()) {
                        System.out.println(b);
                    }
                    break;
                case 3:
                    System.out.println("Please input book's tittle");
                    String tittle = scanner.next();
                    Book b=bookDao.getBookByTittle(tittle);
                    System.out.println(b);
                    break;

            }
        }


    }
}
