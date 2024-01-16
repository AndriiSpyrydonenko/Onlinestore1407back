package com.svitsmachnogo.api.service;

import com.svitsmachnogo.api.domain.dao.abstractional.GiftSetDAO;
import com.svitsmachnogo.api.domain.entity.GiftSet;
import com.svitsmachnogo.api.service.abstractional.GiftSetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GiftSetServiceImpl implements GiftSetService {

    private final GiftSetDAO giftSetDAO;

    @Override
    @Transactional
    public GiftSet getForMainPage() {
        return giftSetDAO.findById(1).orElseThrow();
    }
}
