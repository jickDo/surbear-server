package com.surbear.external.giftishow.service;


import com.surbear.common.encryption.Aes256Util;
import com.surbear.external.giftishow.client.GiftishowAccessClient;
import com.surbear.external.giftishow.dto.GoodsResponse;
import com.surbear.external.giftishow.dto.GoodsResponseList;
import com.surbear.external.giftishow.dto.GoodsDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GiftishowService {

    private final GiftishowAccessClient client;
    private final Aes256Util aes256Util;


    public GoodsResponseList getGoodsList() throws Exception {
        return client.getGoodsList(
                GET_GOODS_LIST,
                AUTHORIZATION_KEY,
                TOKEN_KEY,
                "N",
                "1",
                "10"
        );
    }

    public GoodsResponse getGoods(String goodsCode) throws Exception {
        return client.getGood(
                goodsCode,
                GET_GOODS,
                AUTHORIZATION_KEY,
                TOKEN_KEY,
                "N"
        );
    }


    @Value("${giftishow.key.authorization}")
    private String AUTHORIZATION_KEY;

    @Value("${giftishow.key.token}")
    private String TOKEN_KEY;

    private final static String GET_GOODS_LIST = "0101";
    private final static String GET_GOODS = "0111";


}
