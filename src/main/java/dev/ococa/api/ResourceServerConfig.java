package dev.ococa.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class ResourceServerConfig {

    @Value("${ococa.cors.allowed-origin}")
    private String allowedOrigin;

    @Value("${auth.jwt.issuer-location}")
    private String issuerLocation;
    
    @Bean
    public SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                .requestMatchers("/actuator/prometheus").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
                jwt.decoder(jwtDecoder());
            }))
            // CORS設定
            .cors(cors -> cors
                .configurationSource(corsConfigurationSource())
            );
        return http.build();
    }

    // JWTデコーダーを定義するメソッド
    // リソースサーバーが受け取ったJWTトークンを検証・解析するために使用する
    @Bean
    public JwtDecoder jwtDecoder() {
        // 発行者のURLからJWTデコーダーを生成する.
        // JwtDecoders.fromIssuerLocationメソッドは、指定された発行者（issuer）の情報を使って、
        // JWTの検証に必要な公開鍵や設定を自動的に取得する.
        // これによりトークンの署名を検証し、トークンが信頼できるものであることを確認できる.
        return JwtDecoders.fromIssuerLocation(issuerLocation);
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(allowedOrigin));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}