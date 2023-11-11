package Mirthon.Oasis_back.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class KakaoUserDetails implements UserDetails {

    private User user;

    public KakaoUserDetails(User user) {
        this.user = user;
    }
    private Long id;
    private String kakaoId;
    private String nickname;
    private String email;

    public KakaoUserDetails(Long id, String kakaoId, String nickname, String email) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.nickname = nickname;
        this.email = email;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return kakaoId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

