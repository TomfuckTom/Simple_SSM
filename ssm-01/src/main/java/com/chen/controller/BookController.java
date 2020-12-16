package com.chen.controller;

import com.chen.pojo.Books;
import com.chen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    @RequestMapping("/allBook")
    public String queryBook(Model model){
        List<Books> books = bookService.queryAllBook();
        model.addAttribute("msg",books);
        return "books";
    }
    @RequestMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addPaper(Books books) {
        System.out.println(books);
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }
    @RequestMapping("/toUpdateBook")
    public String toUpdatePager(int id,Model model){
        Books books = bookService.queryBookById(id);
        model.addAttribute("books",books);
        return "updateBook";
    }

    @PostMapping("/updateBook")
    public String updateBook(Books books){
        bookService.updateBook(books);
        return "redirect:/book/allBook";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(int id){
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    @PostMapping("/queryBook")
    public String queryBook(String queryBookName,Model model){
        Books books = bookService.queryBookByName(queryBookName);
        List<Books> list = new ArrayList<Books>();
        list.add(books);
        if (books==null){
            list=bookService.queryAllBook();
        }
        model.addAttribute("msg",list);
        return "books";
    }

}
