//package co.istad.matra.ecommerce.security;
//
//import co.istad.matra.ecommerce.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.jspecify.annotations.NullMarked;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @NullMarked
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // TODO: select user from database table by username
//        User foundUser = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        log.info("foundUser: {}", foundUser.getUsername());
//        log.info("foundUser: {}", foundUser.getRoles());
//        log.info("foundUser: {}", foundUser.getEncryptedPassword());
//
//        // ROLE_ADMIN ROLE_BUSINESS ROLE_CUSTOMER
//        String roles = foundUser.getRoles().stream()
//                .map(Role::getName)
//                .collect(Collectors.joining(" "));
//
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(foundUser.getUsername())
//                .password(foundUser.getEncryptedPassword())
//                .roles(roles)
//                .build();
//    }
//
//}
