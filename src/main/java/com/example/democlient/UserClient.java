package com.example.democlient;

import feign.RequestLine;
import feign.Response;

import java.net.URI;

public interface UserClient {

    @RequestLine(value = "GET")
    Response getUsers(URI uri);
    @RequestLine(value = "GET")
    Response getAdmin(URI uri);
}
