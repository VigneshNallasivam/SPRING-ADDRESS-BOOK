package com.spring.basics.controller;
import com.spring.basics.dto.BookDTO;
import com.spring.basics.dto.ResponseDTO;
import com.spring.basics.entity.BookModel;
import com.spring.basics.service.IBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
public class BookController
{
    @Autowired
    IBookService service;
    @PostMapping("/insertUser")
    public ResponseEntity<ResponseDTO> upload(@Valid @RequestBody BookDTO bookDTO)
    {
        BookModel bookModel =service.upload(bookDTO);
        ResponseDTO responseDTO=new ResponseDTO("User details uploaded successfully!",bookModel);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ResponseDTO> updateById(@Valid @RequestBody BookDTO bookDTO,@PathVariable int id)
    {
        BookModel bookModel=service.updateById(bookDTO,id);
        ResponseDTO responseDTO=new ResponseDTO("Address book details are edited!",bookModel);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }
    @GetMapping ("/allDetails")
    public ResponseEntity<ResponseDTO> getAll()
    {
        List<BookModel> bookModelList=service.getAll();
        ResponseDTO responseDTO=new ResponseDTO("All User details found!",bookModelList);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable int id)
    {
        Optional<BookModel> optionalBookModel=service.findById(id);
        ResponseDTO responseDTO=new ResponseDTO("User details is found!",optionalBookModel);
        return new ResponseEntity(responseDTO,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUserById(@PathVariable int id)
    {
        service.deleteById(id);
        ResponseDTO responseDTO=new ResponseDTO("User details is deleted!","Deleted user id: "+id);
        return new ResponseEntity<>(responseDTO,HttpStatus.GONE);
    }
    @GetMapping("/findUserByAddress/{address}")
    public ResponseEntity<ResponseDTO> findUserByAddress(@PathVariable String address)
    {
        List<BookModel> bookModelList=service.findUserByAddress(address);
        ResponseDTO responseDTO=new ResponseDTO("User details is found by address!",bookModelList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @GetMapping("/findUserByCity/{city}")
    public ResponseEntity<ResponseDTO> findUserByCity(@PathVariable String city)
    {
        List<BookModel> bookModelList=service.findUserByCity(city);
        ResponseDTO responseDTO=new ResponseDTO("User details is found by city!",bookModelList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @GetMapping("/findUserByState/{state}")
    public ResponseEntity<ResponseDTO> findUserByState(@PathVariable String state)
    {
        List<BookModel> bookModelList=service.findUserByState(state);
        ResponseDTO responseDTO=new ResponseDTO("User details is found by state!",bookModelList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @GetMapping("/findUserByZip/{zip}")
    public ResponseEntity<ResponseDTO> findUserByZip(@PathVariable String zip)
    {
        List<BookModel> bookModelList=service.findUserByZip(zip);
        ResponseDTO responseDTO=new ResponseDTO("User details is found by zipCode!",bookModelList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @PostMapping ("/upload")
    public ResponseEntity<ResponseDTO> uploadByToken(@Valid @RequestBody BookDTO bookDTO) throws Exception
    {
        String token=service.uploadByToken(bookDTO);
        ResponseDTO responseDTO=new ResponseDTO("Details uploaded!",token);
        return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
    }
    @GetMapping("/getAllByToken/{token}")
    public ResponseEntity<ResponseDTO> getByToken(@PathVariable String token)
    {
        List<BookModel> bookModelList=service.getByToken(token);
        ResponseDTO responseDTO=new ResponseDTO("Details found!",bookModelList);
        return new ResponseEntity<>(responseDTO,HttpStatus.FOUND);
    }
    @GetMapping("/findByToken/{token}")
    public ResponseEntity<ResponseDTO> findByToken(@PathVariable String token)
    {
        BookModel bookModel=service.findByToken(token);
        ResponseDTO responseDTO=new ResponseDTO("Details found with token!",bookModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
}
