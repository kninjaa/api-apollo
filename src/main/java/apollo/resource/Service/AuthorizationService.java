package apollo.resource.Service;

import apollo.repository.Interface.Iaccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    Iaccount iaccount;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return iaccount.findByLogin(username);
    }
}
