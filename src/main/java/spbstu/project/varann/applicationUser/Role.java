package spbstu.project.varann.applicationUser;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(ApplicationAuthority.ANNOTATE, ApplicationAuthority.POST)),
    USER(Set.of(ApplicationAuthority.ANNOTATE));

    @Getter
    private Set<GrantedAuthority> authorities;

    private Role(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
