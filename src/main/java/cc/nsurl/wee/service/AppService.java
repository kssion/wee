package cc.nsurl.wee.service;

import cc.nsurl.wee.mapper.AppMapper;
import cc.nsurl.wee.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    private final AppMapper mapper;

    @Autowired
    public AppService(AppMapper mapper) {
        this.mapper = mapper;
    }

    public List<App> getApps() {
        return mapper.getApps();
    }

    public boolean insert(App app) {
        return mapper.insert(app) > 0;
    }

    public App selectAppWithSid(String sid) {
        return mapper.select(sid);
    }

    public boolean updateApp(App app) {
        return mapper.update(app) > 0;
    }
}
