package store.zabbix.toolseurekaclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class MyUserDetails extends User implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<Role> roles;

    public MyUserDetails(User user, List<Role> roles) {
        super(user);
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles==null || roles.size()<1) {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
        }
        StringBuilder commaBuilder = new StringBuilder();
        for(Role role : roles){
            commaBuilder.append("ROLE_").append(role.getType()).append(",");
        }
        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }

    /**
     * 帐户未过期
     **/
    @Override
    public boolean isAccountNonExpired() {
        return Boolean.valueOf(super.getAccount());
    }

    /**
     * 是帐户非锁定
     **/
    @Override
    public boolean isAccountNonLocked() {
        return Boolean.valueOf(super.getLocked());
    }

    /**
     * 凭证
     **/
    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.valueOf(super.getCredentials());
    }

    /**
     * 是否启用
     **/
    @Override
    public boolean isEnabled() {
        return Boolean.valueOf(super.getEnable());
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }


}
