package com.example.Zapy.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Zapy.DTO.ZapDTO;
import com.example.Zapy.Repository.AppUserRepository;
import com.example.Zapy.Repository.ZapRepo;
import com.example.Zapy.model.User;
import com.example.Zapy.model.Zap;
import com.example.Zapy.service.ZapInfoService;

@RestController
@RequestMapping("/api/zaps")
public class GetZapDetailsController {

    private final AppUserRepository userRepo;
    private final ZapRepo zapRepo;
    private final ZapInfoService zapService;

    public GetZapDetailsController(
            AppUserRepository userRepo,
            ZapRepo zapRepo,
            ZapInfoService zapService
    ) {
        this.userRepo = userRepo;
        this.zapRepo = zapRepo;
        this.zapService = zapService;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ZapDTO> getZap(
            @PathVariable String projectId,
            @AuthenticationPrincipal OAuth2User principal
    ) {
        // 1) Get current user from OAuth principal
        String googleId = principal.getAttribute("sub");

        User user = userRepo.findByGoogleId(googleId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2) Find Zap by projectId
        Zap zap = zapRepo.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("Zap not found"));

        // 3) Ownership check
        if (!user.getId().equals(zap.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // 4) Load DTO via service and return
        return zapService.getZap(zap.getId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
