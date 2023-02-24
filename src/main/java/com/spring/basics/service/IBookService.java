package com.spring.basics.service;
import com.spring.basics.dto.BookDTO;
import com.spring.basics.entity.BookModel;
import java.util.List;
import java.util.Optional;
public interface IBookService
{
    BookModel upload(BookDTO bookDTO);
    BookModel updateById(BookDTO bookDTO,int id);
    List<BookModel> getAll();
    Optional<BookModel> findById(int id);
    void deleteById(int id);

    List<BookModel> findUserByCity(String city);

    String uploadByToken(BookDTO bookDTO) throws Exception;

    List<BookModel> getByToken(String token);

    BookModel findByToken(String token);

    List<BookModel> findUserByState(String state);

    List<BookModel> findUserByAddress(String address);

    List<BookModel> findUserByZip(String zip);
}
