package com.project.secure.capita.repository.impl;

import com.project.secure.capita.domain.User;
import com.project.secure.capita.exception.ApiException;
import com.project.secure.capita.repository.RoleRepository;
import com.project.secure.capita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static com.project.secure.capita.enumeration.RoleType.ROLE_USER;
import static com.project.secure.capita.enumeration.VerificationType.ACCOUNT;
import static com.project.secure.capita.query.Query.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository<User> {

    private final RoleRepository roleRepository;

    private final NamedParameterJdbcTemplate jdbc;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User create(User user) throws ApiException {
        if (getEmailAccount(user.getEmail().trim().toLowerCase()) > 0) {
            throw new ApiException("Email already in use. Please use another email.");
        }

        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameterSource = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameterSource, holder);

            user.setId(holder.getKey().longValue());
            roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());

            String verificationURL = getVerificationURL(UUID.randomUUID().toString(), ACCOUNT.getType());
            jdbc.update(INSERT_VERIFICATION_QUERY, Map.of("userId", user.getId(), "url", verificationURL));

            // emailService.sendVerificationURL(user.getFirstName(), user.getEmail(), verificationURL, ACCOUNT);

            user.setEnabled(false);
            user.setNotLocked(true);

            return user;
        } catch (Exception exception) {
            throw new ApiException("An error occurred. Please try again.");
        }
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public Collection<User> list(int page, int pageSize) {
        return null;
    }

    private int getEmailAccount(String email) {
        return jdbc.queryForObject(COUNT_USE_EMAIL_QUERY, Map.of("email", email), Integer.class);
    }

    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstname", user.getFirstName())
                .addValue("lastname", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()));
    }

    private String getVerificationURL(String key, String type) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/" + type + "/" + key).toUriString();
    }
}
