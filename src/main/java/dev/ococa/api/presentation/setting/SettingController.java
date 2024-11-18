package dev.ococa.api.presentation.setting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.ococa.api.domain.model.setting.SettingEntity;
import dev.ococa.api.domain.model.setting.SettingRepository;

@RestController
public class SettingController {
    @Autowired
    SettingRepository settingRepository;

    @GetMapping("/api/setting")
    public SettingEntity getSettingByUserId(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return settingRepository.getSettingByUserId(userId);
    }
}
