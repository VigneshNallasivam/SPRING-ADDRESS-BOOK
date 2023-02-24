package com.spring.basics.repository;
import com.spring.basics.entity.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BookRepo extends JpaRepository<BookModel,Integer>
{
    @Query(value = "select * from book.address_book,book.cities where address_book.id=cities.id and city= :city", nativeQuery = true)
    List<BookModel> findUserByCity(String city);
    @Query(value = "select * from book.address_book,book.states where address_book.id=states.id and state= :state", nativeQuery = true)
    List<BookModel> findUserByState(String state);
    @Query(value = "select * from book.address_book,book.addresses where address_book.id=addresses.id and address= :address", nativeQuery = true)
    List<BookModel> findUserByAddress(String address);
    @Query(value = "select * from book.address_book,book.zipCodes where address_book.id=zipCodes.id and zipCode= :zipCode", nativeQuery = true)
    List<BookModel> findUserByZip(String zip);

}
