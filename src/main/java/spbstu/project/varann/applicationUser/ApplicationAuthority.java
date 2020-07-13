package spbstu.project.varann.applicationUser;

import org.springframework.security.core.GrantedAuthority;

public enum ApplicationAuthority implements GrantedAuthority {
    POST("POST"),
    ANNOTATE("ANNOTATE");

    private String  authority;

    private ApplicationAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
