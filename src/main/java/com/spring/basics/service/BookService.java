package com.spring.basics.service;
import com.spring.basics.dto.BookDTO;
import com.spring.basics.entity.BookModel;
import com.spring.basics.exception.BookException;
import com.spring.basics.repository.BookRepo;
import com.spring.basics.utility.EmailSender;
import com.spring.basics.utility.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class BookService implements IBookService
{
    @Autowired
    BookRepo repo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSender senderService;
    @Override
    public BookModel upload(BookDTO bookDTO) 
    {
        BookModel bookModel=new BookModel(bookDTO);
        repo.save(bookModel);
        senderService.sendEmail(bookModel.getEmail(),"Test Mail", "Hii...."+bookModel.getName()+" your details are added!\n\n Name:  "+bookModel.getName()+"\n Phone number:  "+bookModel.getPhoneNumber()+"\n Email:  "+bookModel.getEmail()+"\n Address:  "+bookModel.getAddress()+"\n City:  "+bookModel.getCity()+"\n State:  "+bookModel.getState()+"\n ZipCode:  "+bookModel.getZipCode());
        return bookModel;
    }
    @Override
    public BookModel updateById(BookDTO bookDTO,int id) 
    {
        BookModel bookModel=repo.findById(id).get();
        if(repo.findById(id).isPresent())
        {
            bookModel.setName(bookDTO.getName());
            bookModel.setPhoneNumber(bookDTO.getPhoneNumber());
            bookModel.setEmail(bookDTO.getEmail());
            bookModel.setAddress(bookDTO.getAddress());
            bookModel.setCity(bookDTO.getCity());
            bookModel.setState(bookDTO.getState());
            bookModel.setZipCode(bookDTO.getZipCode());
            senderService.sendEmail(bookModel.getEmail(),"Test Mail","Hii...."+bookModel.getName()+" your details has been edited!\n\n Name:  "+bookModel.getName()+"\n Phone number:  "+bookModel.getPhoneNumber()+"\n  Email:  "+bookModel.getEmail()+"\n Address:  "+bookModel.getAddress()+"\n City:  "+bookModel.getCity()+"\n State:  "+bookModel.getState()+"\n ZipCode:  "+bookModel.getZipCode());
            return repo.save(bookModel);
        }
        else
        {
            throw new BookException("Sorry! Cannot find user id: "+id);
        }
    }
    @Override
    public List<BookModel> getAll()
    {
        List<BookModel> bookModel=repo.findAll();
        return bookModel;
    }
    @Override
    public Optional<BookModel> findById(int id)
    {
        Optional<BookModel> bookModel=repo.findById(id);
        if(bookModel.isPresent())
        {
            return repo.findById(id);
        }
        else
        {
            throw new BookException("Sorry! Cannot find user id: "+id);
        }
    }
    @Override
    public void deleteById(int id)
    {
        Optional<BookModel> bookModel=repo.findById(id);
        if(bookModel.isPresent())
        {
            repo.deleteById(id);
            senderService.sendEmail(bookModel.get().getEmail(), "Test Mail","Hii....! Your details has been deleted!");
        }
        else
        {
            throw new BookException("Sorry! Cannot find user id: " + id);
        }
    }
    @Override
    public List<BookModel> findUserByAddress(String address)
    {
        List<BookModel> getByAddress=repo.findUserByAddress(address);
        if(getByAddress.isEmpty())
        {
            throw new BookException("Sorry! Can not find user address: "+address);
        }
        else
        {
            return repo.findUserByAddress(address);
        }
    }
    @Override
    public List<BookModel> findUserByCity(String city)
    {
        List<BookModel> getByCity=repo.findUserByCity(city);
        if(getByCity.isEmpty())
        {
            throw new ArithmeticException("Sorry! Can not find user city: "+city);
        }
        else
        {
            return repo.findUserByCity(city);
        }
    }
    @Override
    public List<BookModel> findUserByState(String state)
    {
        List<BookModel> getByState=repo.findUserByState(state);
        if(getByState.isEmpty())
        {
            throw new BookException("Sorry! Can not find user state: "+state);
        }
        else
        {
            return repo.findUserByState(state);
        }
    }
    @Override
    public List<BookModel> findUserByZip(String zip)
    {
        List<BookModel> getByZip=repo.findUserByZip(zip);
        if(getByZip.isEmpty())
        {
            throw new BookException("Sorry! Can not find user zipcode: "+zip);
        }else {
            return repo.findUserByZip(zip);
        }
    }
    @Override
    public String uploadByToken(BookDTO bookDTO) throws Exception
    {
        BookModel bookModel=new BookModel(bookDTO);
        repo.save(bookModel);
        String token=tokenUtil.createToken(bookModel.getId());
        senderService.sendEmail(bookModel.getEmail(),"Test Mail","Hii...."+bookModel.getName()+" your details are added!\n\n Name:  "+bookModel.getName()+"\n Phone number:  "+bookModel.getPhoneNumber()+"\n Email:  "+bookModel.getEmail()+"\n Address:  "+bookModel.getAddress()+"\n City:  "+bookModel.getCity()+"\n  State:  "+bookModel.getState()+"\n ZipCode:  "+bookModel.getZipCode());
        return token;
    }
    @Override
    public List<BookModel> getByToken(String token)
    {
        int id=tokenUtil.decodeToken(token);
        Optional<BookModel> isUserPresent=repo.findById(id);
        if(isUserPresent.isPresent())
        {
            List<BookModel> bookModelList=repo.findAll();
            return bookModelList;
        }
        else
        {
            System.out.println("Exception........!! Token not found!");
            return null;
        }
    }

    @Override
    public BookModel findByToken(String token)
    {
        int id=tokenUtil.decodeToken(token);
        Optional<BookModel> bookModel=repo.findById(id);
        if(bookModel.isPresent())
        {
            return bookModel.get();
        }
        else
        {
            throw new BookException("Sorry! cannot find the id: "+id);
        }
    }

}
