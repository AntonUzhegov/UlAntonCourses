package ru.ulanton.courses.ulanton_courses.services;


import org.slf4j.Logger;
import ru.ulanton.courses.ulanton_courses.models.User;
import ru.ulanton.courses.ulanton_courses.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Loading user by username: {}", username);

        // Пытаемся найти пользователя по username
        User user = userRepository.findByUsername(username)
                .or(() -> {
                    // Если не нашли по username, пробуем по email
                    logger.debug("User not found by username {}, trying by email", username);
                    return userRepository.findByEmail(username);
                })
                .orElseThrow(() -> {
                    logger.error("User not found with username/email: {}", username);
                    return new UsernameNotFoundException("User not found with username: " + username);
                });

        logger.debug("User found: {}", user.getUsername());

        // Обновляем время последнего входа
        user.setLastLogin(java.time.LocalDateTime.now());
        userRepository.save(user);

        return user;
    }
}