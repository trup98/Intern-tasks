package feignClient.external;

import org.openapitools.api.ApiApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "FeignClientService",url = "http://localhost:9090")
public interface FeignClientService extends ApiApi {

}
