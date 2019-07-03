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

    @GetMapping("/asyncDependencyCallWithApacheHttpClient4")
    public DeferredResult<Integer> asyncDependencyCallWithApacheHttpClient4() throws IOException {
        DeferredResult<Integer> deferredResult = new DeferredResult<>();
        testBean.asyncDependencyCallWithApacheHttpClient4(deferredResult);
        return deferredResult;
    }

    @GetMapping("/asyncDependencyCallWithApacheHttpClient3")
    public DeferredResult<Integer> asyncDependencyCallWithApacheHttpClient3() throws IOException {
        DeferredResult<Integer> deferredResult = new DeferredResult<>();
        testBean.asyncDependencyCallWithApacheHttpClient3(deferredResult);
        return deferredResult;
    }

    @GetMapping("/asyncDependencyCallWithOkHttp3")
    public DeferredResult<Integer> asyncDependencyCallWithOkHttp3() throws IOException {
        DeferredResult<Integer> deferredResult = new DeferredResult<>();
        testBean.asyncDependencyCallWithOkHttp3(deferredResult);
        return deferredResult;
    }

    @GetMapping("/asyncDependencyCallWithOkHttp2")
    public DeferredResult<Integer> asyncDependencyCallWithOkHttp2() throws IOException {
        DeferredResult<Integer> deferredResult = new DeferredResult<>();
        testBean.asyncDependencyCallWithOkHttp2(deferredResult);
        return deferredResult;
    }

    @GetMapping("/asyncDependencyCallWithHttpURLConnection")
    public DeferredResult<Integer> asyncDependencyCallWithHttpURLConnection() throws IOException {
        DeferredResult<Integer> deferredResult = new DeferredResult<>();
         testBean.asyncDependencyCallWithHttpURLConnection(deferredResult);
        return deferredResult;
    }
}
