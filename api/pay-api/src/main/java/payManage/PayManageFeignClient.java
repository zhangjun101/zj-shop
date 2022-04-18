package payManage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import result.Result;

/**
 * @FeginClient
 * name：服务提供者的名称
 * 声明需要调用的微服务名称
 */
@FeignClient(value = "pay-server")
public interface PayManageFeignClient {

    @RequestMapping(value = "/payManage/getAllPays",method = RequestMethod.GET)
    public Result getAllPays();

}