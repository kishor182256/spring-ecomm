package com.example.Ecommerce.Ecommerce.helper;


import com.example.Ecommerce.Ecommerce.enums.Roles;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class CheckCurrentUserRole {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    private String extractToken() {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private Roles getCurrentUserRole() {
        String token = extractToken();
        if (token == null || !jwtUtil.validateToken(token)) {
            return null;
        }

        Claims claims = jwtUtil.getAllClaims(token);
        String roleString = claims.get("role", String.class);
        return Roles.fromString(roleString);
    }

    public boolean isAdmin() {
        Roles role = getCurrentUserRole();
        return role == Roles.ADMIN || role == Roles.SUPER_ADMIN;
    }

    public boolean isUser() {
        Roles role = getCurrentUserRole();
        return role == Roles.USER;
    }

    public boolean isModerator() {
        Roles role = getCurrentUserRole();
        return role == Roles.MODERATOR;
    }

    public boolean isSuperAdmin() {
        Roles role = getCurrentUserRole();
        return role == Roles.SUPER_ADMIN;
    }

    public Roles getRole() {
        return getCurrentUserRole();
    }
}
