package com.msioja.thesisresv.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class PrincipalService {
    public static String getCurrentlyLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
