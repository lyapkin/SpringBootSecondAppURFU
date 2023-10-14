package ru.liapkin.springbootsecondappurfu.service;

import org.springframework.stereotype.Service;
import ru.liapkin.springbootsecondappurfu.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
