package ru.liapkin.springbootsecondappurfu.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.liapkin.springbootsecondappurfu.exception.ValidationFailedException;

@Service
public interface ValidationService {

    void isValid(BindingResult bindingResult) throws ValidationFailedException;

}
