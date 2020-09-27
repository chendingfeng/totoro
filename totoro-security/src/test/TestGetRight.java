import com.dingfeng.totoro.TotoroSecurityApplication;
import com.dingfeng.totoro.model.Right;
import com.dingfeng.totoro.service.RightService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TotoroSecurityApplication.class)
public class TestGetRight {

    @Resource
    private RightService rightService;

    @Test
    public void test(){
        List<Right> result = rightService.findByUsername("admin");
        System.err.println(result);
    }


}