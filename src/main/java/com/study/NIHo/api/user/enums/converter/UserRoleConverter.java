package com.study.NIHo.api.user.enums.converter;

import com.study.NIHo.api.user.enums.UserRole;
import org.springframework.core.convert.converter.Converter;

/**
 * String to Enum Converter
 */
public class UserRoleConverter implements Converter<String, UserRole> {

    @Override
    public UserRole convert(String userRole) {
        return UserRole.fromUserRole(userRole);
    }

}
