package service;

import javax.annotation.Resource;

import dao.MentorRoomDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.User;
import dao.UserDao;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserDao userDao;
    final MentorRoomDAO mentorRoomDAO;

    @Resource(name = "loginUserBean")
    private User loginUserBean;

    public boolean checkuserIdExist(String user_id) {

        String user_name = userDao.checkUserIdExist(user_id);

        if(user_name == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkuserEmailExist(String user_email) {
        System.out.println(user_email);
        String user_emailCheck = userDao.checkUserEmailExist(user_email);
        
        if(user_emailCheck == null) {
            return true;
        } else {
            return false;
        }
    }

    public void addUserInfo(User joinUserBean) {
        userDao.addUserInfo(joinUserBean);
    }

    public void getLoginUserInfo(User tempLoginUserBean) {

        User tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);

        if(tempLoginUserBean2 != null) {
            loginUserBean.setUser_idx(tempLoginUserBean2.getUser_idx());
            loginUserBean.setUser_name(tempLoginUserBean2.getUser_name());
            loginUserBean.setUserLogin(true);
        }
    }
//    public void getLoginUserInfo(User tempLoginUserBean) {
//
//        User tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);
//
//        if(tempLoginUserBean2 != null) {
//            loginUser.setUser_role(tempLoginUserBean2.getUser_role());
//            loginUser.setUser_name(tempLoginUserBean2.getUser_name());
//            loginUser.setUserLogin(true);
//        }
//    }


    /**
     *  user_id를 넣엇을때 mentee의 아이디면 mentor id를 알려주는 method
    */

}











