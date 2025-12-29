package com.example.Zapy.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Zapy.Repository.AppUserRepository;
import com.example.Zapy.Repository.GetZapRepo;
import com.example.Zapy.model.User;
import com.example.Zapy.model.Zap;

@RestController
@RequestMapping("/zaps")
public class GetUserZapsController {

    private final AppUserRepository userRepo;
    private final GetZapRepo zapRepo;

    public GetUserZapsController(AppUserRepository userRepo, GetZapRepo zapRepo) {
        this.userRepo = userRepo;
        this.zapRepo = zapRepo;
    }

    /**
     * GET /api/zaps/mine
     * Returns list of zaps owned by the current OAuth2 user.
     */
    @GetMapping("/mine")
    public ResponseEntity<List<ZapResponse>> getMyZaps(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(401).build();
        }

        String googleId = principal.getAttribute("sub");
        User user = userRepo.findByGoogleId(googleId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Zap> zaps = zapRepo.findAllByUserId(user.getId());

        List<ZapResponse> dto = zaps.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dto);
    }

    private ZapResponse toDto(Zap zap) {
        // Map only the fields you want to expose
        return new ZapResponse(
            zap.getId(),
            zap.getProjectId(),
            zap.getUserId()
        );
    }

    // Simple DTO; move to its own file if you prefer
    public static class ZapResponse {
        private String id;
        private String projectId;
        private String userId;

        public ZapResponse() {}

        public ZapResponse(String id, String projectId, String userId) {
            this.id = id;
            this.projectId = projectId;
            this.userId = userId;
        }

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getProjectId() { return projectId; }
        public void setProjectId(String projectId) { this.projectId = projectId; }

        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
    }
}
