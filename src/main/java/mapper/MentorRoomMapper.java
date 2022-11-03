package mapper;

import domain.MentorRoom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MentorRoomMapper {

    @Insert("INSERT INTO MentorRoom" +
            "(user_id, num, title," +
            "studyPeriod, studyWeekly, studyTimeStart, studyTimeEnd," +
            "capacity, nowCapacity, career, school, content) " +
            "values (#{user_id}, mentor_room_seq.nextval, #{title}," +
            "#{studyPeriod}, #{studyWeekly}, #{studyTimeStart}, #{studyTimeEnd}," +
            "#{capacity}, 0, #{career}, #{school}, #{content})")
    int createRoom(MentorRoom roomInfo);

    // MentorRoom 방번호 넣으면 MentorRoom 나옴
    @Select("select * from MentorRoom where num = ${num}")
    MentorRoom getUserMentorRoom(int num);

    // iuser_id로 roomNum찾기
    @Select("select num from MentorRoom where user_id = ${user_id}")
    int getRoomNo(String user_id);
}