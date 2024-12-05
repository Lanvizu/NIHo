package com.study.NIHo.api.user.application;

public interface UserDeleteService {
    /**
     * 사용자 삭제
     *
     * @param id 사용자 idx
     */
    void deleteUser(final long id);
}
