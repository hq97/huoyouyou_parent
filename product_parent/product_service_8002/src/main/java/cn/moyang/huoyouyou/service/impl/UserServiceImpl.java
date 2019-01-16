package cn.moyang.huoyouyou.service.impl;

import cn.moyang.huoyouyou.domain.User;
import cn.moyang.huoyouyou.mapper.UserMapper;
import cn.moyang.huoyouyou.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hqtest
 * @since 2019-01-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
