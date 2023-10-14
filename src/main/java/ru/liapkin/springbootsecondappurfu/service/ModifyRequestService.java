package ru.liapkin.springbootsecondappurfu.service;

import org.springframework.stereotype.Service;
import ru.liapkin.springbootsecondappurfu.model.Request;
import ru.liapkin.springbootsecondappurfu.util.DateTimeUtil;

import java.util.Date;

@Service
public interface ModifyRequestService {

    void modify(Request request);
}
