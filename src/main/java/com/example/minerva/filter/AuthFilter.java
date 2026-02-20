package com.example.minerva.filter;
import java.io.IOException;

import com.example.minerva.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req;
        HttpServletResponse res;
        String uri;
        User user;
        String role;


        req = (HttpServletRequest) request;
        res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        uri = req.getRequestURI();

        // permitir login e resgistro sem session
        if(uri.contains("login") || uri.contains("register") || uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }

        if(session == null || session.getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        user = (User) session.getAttribute("user");
        role = user.getRole();

        if(user.isFirstAcess() && "student".equals(role) &&  !uri.contains("/aluno/quiz")){
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/aluno/quiz/quiz.jsp").forward(request, response);
            return;
        }


        if(uri.contains("aluno")) {
            if(!"student".equals(role)) {
                res.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            } else if (uri.contains("quiz") && !user.isFirstAcess()){ res.sendRedirect(req.getContextPath() + "/login.jsp"); return; }

        } else if(uri.contains("professor") || uri.contains("teacher")) {
            if(!"teacher".equals(role)) {
                res.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }
        } else {
            if(!"admin".equals(role)) {
                res.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
