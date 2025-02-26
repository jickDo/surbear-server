package com.surbear.external.giftishow.client;


import com.surbear.external.giftishow.dto.GoodsResponse;
import com.surbear.external.giftishow.dto.GoodsResponseList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("https://bizapi.giftishow.com")
public interface GiftishowAccessClient {

    @PostExchange("/bizApi/goods")
    GoodsResponseList getGoodsList(
            @RequestParam("api_code") String api_code,
            @RequestParam("custom_auth_code") String custom_auth_code,
            @RequestParam("custom_auth_token") String custom_auth_token,
            @RequestParam("dev_yn") String dev_yn,
            @RequestParam("start") String start,
            @RequestParam("size") String size
    );

    @PostExchange("/bizApi/goods/{goods_code}")
    GoodsResponse getGood(
            @PathVariable("goods_code") String goodsCode,
            @RequestParam("api_code") String apiCode,
            @RequestParam("custom_auth_code") String customAuthCode,
            @RequestParam("custom_auth_token") String customAuthToken,
            @RequestParam("dev_yn") String devYn
    );
}
