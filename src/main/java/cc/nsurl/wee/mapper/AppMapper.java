package cc.nsurl.wee.mapper;

import cc.nsurl.wee.model.App;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AppMapper {

    @Select("select * from app where state=0 order by time desc")
    List<App> getApps();

    @Insert("INSERT INTO app(sid, name, path, type, repertory) VALUES (#{sid}, #{name}, #{path}, #{type}, #{repertory})")
    int insert(App app);

    @Select("select * from app where sid=#{sid}")
    App select(String sid);

    @Update("UPDATE app SET name=#{name}, path=#{path},type=#{type},repertory=#{repertory} WHERE sid=#{sid}")
    int update(App app);
}
