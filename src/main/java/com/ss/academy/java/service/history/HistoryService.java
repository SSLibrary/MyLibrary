package com.ss.academy.java.service.history;

import com.ss.academy.java.model.book.Book;
import com.ss.academy.java.model.user.User;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

public interface HistoryService {
    @PreAuthorize(value="hasAuthority('ADMIN', 'USER')")
    public List<Book> showMyHistory(Long userId);

    @PreAuthorize(value="hasAuthority('ADMIN', 'USER')")
    public void getBook(User userId, Book bookId);

    @PreAuthorize(value="hasAuthority('ADMIN', 'USER')")
    public void returnBook(Book bookId);
}