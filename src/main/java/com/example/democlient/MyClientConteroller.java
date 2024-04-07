package com.example.democlient;

import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class MyClientConteroller {
    @Autowired
    UserClient userClient;
    @GetMapping("call")
    public String getData() throws URISyntaxException, IOException {
       Response r= userClient.getUsers(new URI("http://localhost:8087/hello"));

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(r.body().asInputStream()) );
     String s="";
     String response="";
      while((s=bufferedReader.readLine())!=null){
          System.out.println(s+"===");
          response+=s;
      }
       return response;
    }
    @GetMapping("call2")
    public String getData2() throws URISyntaxException, IOException {
       Response r= userClient.getAdmin(new URI("http://localhost:8087/call2"));

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(r.body().asInputStream()) );
     String s="";
     String response="";
      while((s=bufferedReader.readLine())!=null){
          System.out.println(s+"===");
          response+=s;
      }
       return response;
    }
}
