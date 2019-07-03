package com.springbootstartertest.controller;

import java.io.IOException;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class TestController {

    @Autowired
    private TestBean testBean;

    @GetMapping("/")
    public String rootPage() {
        return "OK";
    }

    @GetMapping("/throwsException")
    public void resultCodeTest() throws Exception {
        throw new ServletException("This is an exception");
    }

    @GetMapping("/asyncDependencyCall")
    public DeferredResult<Integer> asyncDependencyCall() throws IOException {
        DeferredResult<Integer> deferredResult = new DeferredResult<>();
        testBean.asyncDependencyCall(deferredResult);
        return deferredResult;
    }
}
