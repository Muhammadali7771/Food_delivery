package com.example.food_delivery.service;

import com.example.food_delivery.configuration.security.JwtTokenUtil;
import com.example.food_delivery.configuration.security.SessionUser;
import com.example.food_delivery.dto.TokenResponse;
import com.example.food_delivery.dto.authuser.AuthUserCreateDto;
import com.example.food_delivery.dto.authuser.AuthUserDto;
import com.example.food_delivery.dto.authuser.AuthUserUpdateDto;
import com.example.food_delivery.dto.authuser.AuthenticationRequest;
import com.example.food_delivery.entity.AuthUser;
import com.example.food_delivery.entity.Cart;
import com.example.food_delivery.enums.ROLE;
import com.example.food_delivery.exception.EmailAlreadyExists;
import com.example.food_delivery.exception.PhoneNumberAlreadyExists;
import com.example.food_delivery.exception.ResourceNotFoundException;
import com.example.food_delivery.mapper.AuthUserMapper;
import com.example.food_delivery.repository.AuthUserRepository;
import com.example.food_delivery.repository.CartRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionUser sessionUser;
    private final AuthUserMapper authUserMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final CartRepository cartRepository;
    private final AuthenticationManager authenticationManager;

    public AuthUserServiceImpl(AuthUserRepository authUserRepository,
                               PasswordEncoder passwordEncoder,
                               SessionUser sessionUser,
                               AuthUserMapper authUserMapper,
                               JwtTokenUtil jwtTokenUtil,
                               CartRepository cartRepository,
                               AuthenticationManager authenticationManager) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionUser = sessionUser;
        this.authUserMapper = authUserMapper;
        this.jwtTokenUtil = jwtTokenUtil;
        this.cartRepository = cartRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public TokenResponse save(AuthUserCreateDto dto) {
        if (authUserRepository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExists(dto.email() + " already exists");
        }
        if (authUserRepository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new PhoneNumberAlreadyExists(dto.phoneNumber() + " already exists");
        }
        AuthUser authUser = authUserMapper.toEntity(dto);
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setRole(ROLE.USER);
        authUserRepository.save(authUser);

        Cart cart = new Cart();
        cart.setAuthUser(authUser);
        cartRepository.save(cart);

        String token = jwtTokenUtil.generateToken(authUser.getEmail());
        return new TokenResponse(token);
    }

    @Override
    public TokenResponse login(AuthenticationRequest authenticationRequest) {
        String email = authenticationRequest.email();
        String password = authenticationRequest.password();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        authenticationManager.authenticate(authenticationToken);
        String token = jwtTokenUtil.generateToken(email);
        return new TokenResponse(token);
    }

    @Override
    public AuthUser getById(Integer id) {
        return authUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void update(AuthUserUpdateDto dto) {
        Integer id = sessionUser.getId();
        AuthUser authUser = authUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        AuthUser authUserUpdated = authUserMapper.partialUpdate(dto, authUser);
        authUserUpdated.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUserRepository.save(authUserUpdated);
    }

    @Override
    public AuthUserDto getUserProfile() {
        Integer userId = sessionUser.getId();
        AuthUser user = authUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return authUserMapper.toDto(user);
    }
}
