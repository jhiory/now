package doit.now.mapper;

import doit.now.security.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface  UserMapper {
    List<User> getUserList() throws Exception;
}


