package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.GiftSetDAO;
import com.svitsmachnogo.api.domain.entity.GiftSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiftSetServiceImpl implements GiftSetService {

    @Autowired
    GiftSetDAO giftSetDAO;

    @Override
    public GiftSet getForMainPage() {
        return giftSetDAO.findById(1).get();
    }
}
