package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.GiftSetDAO;
import com.svitsmachnogo.api.domain.entity.GiftSet;
import com.svitsmachnogo.api.service.abstractional.GiftSetService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiftSetServiceImpl implements GiftSetService {

    @Autowired
    GiftSetDAO giftSetDAO;

    @Override
    @Transactional
    public GiftSet getForMainPage() {
        return giftSetDAO.findById(1).get();
    }
}
