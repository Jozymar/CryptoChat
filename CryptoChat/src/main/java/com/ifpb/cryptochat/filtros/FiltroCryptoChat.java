//package com.ifpb.cryptochat.filtros;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebFilter(filterName = "filtroCryptoChat",
//        urlPatterns = {"/mensagem.xhtml", "/pesquisa.xhtml"})
//public class FiltroCryptoChat implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response,
//            FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpSession session = ((HttpServletRequest) request).getSession();
//
//        String usuario = (String) session.getAttribute("email");
//
//        if (usuario == null) {
//            ((HttpServletResponse) response).sendRedirect("index.xhtml");
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
