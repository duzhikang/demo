package com.dzk.web.servlet;/**
 * Created by dzk on 2020/2/13.
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName MyServlet
 * @Description
 * @Author dzk
 * @Version v1
 * @Date 2020/2/13
 **/
@WebServlet(urlPatterns = "/servlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().print("hello rudy!!!");
    }


}
