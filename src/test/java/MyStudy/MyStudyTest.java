package MyStudy;

import config.ServletAppContext;
import domain.HomeWork;
import domain.MentoRoom;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import mapper.MyStudyMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServletAppContext.class})
@WebAppConfiguration
@Log4j
public class MyStudyTest {

    @Autowired
    MyStudyMapper mapper;

    @Test
    public void mapperTest(){
        assertThat(mapper, is(notNullValue()));
    }

    @Test
    public void select_test(){
        String user_id = "멘토철수";
        MentoRoom mentoRoom = mapper.getMyStudyRoom(user_id);
        assertThat(user_id, is(mentoRoom.getName()));
    }

}