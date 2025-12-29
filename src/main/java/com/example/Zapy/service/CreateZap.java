package com.example.Zapy.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.Zapy.Repository.AppUserRepository;
import com.example.Zapy.Repository.ZapRepo;
import com.example.Zapy.model.User;
import com.example.Zapy.model.Zap;

@Service
public class CreateZap {

    private final ZapRepo zapRepo;
    private final AppUserRepository userRepo;

    public CreateZap(ZapRepo zapRepo, AppUserRepository userRepo) {
        this.zapRepo = zapRepo;
        this.userRepo = userRepo;
    }

    public void create(String projectId) {

        // 1) Get current authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        OAuth2User oAuth2User = (OAuth2User) auth.getPrincipal();

        // 2) Extract googleId from OAuth user
        String googleId = oAuth2User.getAttribute("sub");

        // 3) Find User record
        User user = userRepo.findByGoogleId(googleId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 4) Create Zap and link to user
        Zap zap = new Zap();
        zap.setProjectId(projectId);
        zap.setUserId(user.getId());   // make sure Zap has userId field + getter/setter

        zapRepo.save(zap);
    }
}
