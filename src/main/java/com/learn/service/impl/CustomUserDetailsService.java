package com.learn.service.impl;

import com.learn.constant.SystemConstant;
import com.learn.dto.MyUser;
import com.learn.entity.RoleEntity;
import com.learn.entity.UserEntity;
import com.learn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);

        if(userEntity == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        //Sau khi thực hiện xác thực thành công tài khoản,
        //Các thông tin của user được đẩy vào spring security
        //TODO

        //Lấy các quyền có trong user đó
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(RoleEntity role : userEntity.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        };

        MyUser user = new MyUser(userEntity.getUsername(), userEntity.getPassword(),
                true,true,true, true, authorities);
        user.setFullName(userEntity.getFullName());
        return user;
    }
}
