package com.tanyang.twitter.servlet;

import com.tanyang.twitter.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class ServletTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName("aww");
        user.setBirthday(new Date(System.currentTimeMillis()));
        resp.setContentType("text/html;charset=utf-8");
//        resp.getWriter().write(JSON.toString(user));
        resp.getWriter().write(1/0);
    }
}
