package com.mezzi.carsnav.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoginMiddleware extends OncePerRequestFilter {

    private static final Map<String, List<String>> roleAccessMap = new HashMap<>();

    static {
        roleAccessMap.put("admin", List.of("/admin_dashboard", "/dashboard", "/offer_consultation_admin",
                "/request_consultation", "/admin_list_request", "/edit_company/*", "/list_company",
                  "/editcompany/", "/list_user", "/edit_user", "/deletecompany", "/company",
                "/company-details", "/logout", "/unauthorized" ));

        roleAccessMap.put("admin_company", List.of("/admin_company_dashboard", "/dashboard", "/company-add-offer",
                "/request_consultation", "/unauthorized", "/add-offer", "/cancel-request",
                "/traiter-request", "/logout"));

        roleAccessMap.put("user", List.of("/user_dashboard", "/dashboard", "/offer-consultation",
                "/subscriptions/my-subscriptions","/subscriptions/cancel", "/unauthorized", "/register", "/login", "/index",
                "/", "/add-request", "/user_request_consultation", "/subscribe", "/logout"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        String userRole = (session != null) ? (String) session.getAttribute("role") : null;
        String requestURI = request.getRequestURI();

        // Allow unauthenticated access to login and registration pages
        if (requestURI.equals("/") || requestURI.equals("/login") || requestURI.equals("/register") || requestURI.startsWith("/index")
                || requestURI.startsWith("/admin")  || requestURI.startsWith("/add") || requestURI.startsWith("/edit")|| requestURI.startsWith("/notifications")
                ||requestURI.startsWith("/swagger-ui")
                 || requestURI.startsWith("/v3/api-docs")
                ||  requestURI.startsWith("/swagger-resources")
                ||  requestURI.startsWith("/webjars/")
  ) {
            filterChain.doFilter(request, response);
            return;
        }

        // Allow static resources
        if (requestURI.startsWith("/assets/") || requestURI.startsWith("/vendor/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Redirect unauthenticated users
        if (userRole == null) {
            response.sendRedirect("/login");
            return;
        }

        // Check role-based access
        boolean isAllowed = roleAccessMap.getOrDefault(userRole, List.of()).contains(requestURI);

        if (!isAllowed) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendRedirect(request.getContextPath() + "/unauthorized");
            response.flushBuffer(); // Ensure response is sent immediately
            return;
        }

        filterChain.doFilter(request, response);
    }
}


//package com.mezzi.carsnav.Config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class LoginMiddleware extends OncePerRequestFilter {
//
//    private static final Map<String, List<String>> roleAccessMap = new HashMap<>();
//
//    static {
//        roleAccessMap.put("admin", List.of("/admin_dashboard","/dashboard","/offer_consultation_admin"
//                                            ,"/request_consultation","/admin_list_request","/edit_company","/list_company"
//                                            ,"/editcompany","/list_user","/edit_user","/deletecompany","/company","/company-details","/logout","/unauthorized"));
//
//        roleAccessMap.put("admin_company", List.of("/admin_company_dashboard","/dashboard","/company-add-offer","/request_consultation","/unauthorized"
//                                            ,"/add-offer","/cancel-request","/traiter-request","/logout"));
//
//        roleAccessMap.put("user", List.of("/user_dashboard","/dashboard","/offer-consultation","/subscriptions/my-subscriptions","/unauthorized","/register","/login","/index","/","/assets/","/vendor/"
//                                        ,"/add-request","/user_request_consultation","/subscribe","/logout"));
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//        String userRole = (session != null) ? (String) session.getAttribute("role") : null;
//
//        String requestURI = request.getRequestURI();
//
//
//        if (userRole == null) {
//            response.sendRedirect("/login");
//            return;
//        }
//
//        boolean isAllowed = roleAccessMap.getOrDefault(userRole, List.of()).stream()
//                .anyMatch(url -> requestURI.startsWith(url));
//
//        if (!isAllowed) {
//            response.sendRedirect("/unauthorized");
//            return;
//        }
//
//
//        filterChain.doFilter(request, response);
//    }
//}
