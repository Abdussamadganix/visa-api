package com.visa.service.service;

import com.visa.service.exception.ConflictException;
import com.visa.service.exception.NotFoundException;
import com.visa.service.model.entity.MerchantAlias;
import com.visa.service.model.request.MerchantSearchRequest;
import com.visa.service.model.request.UserSearchRequest;
import com.visa.service.repository.MerchantAliasRepository;
import com.visa.service.util.VisaBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Abdussamad
 */
@Service
public class MerchantAliasService {

  private final MerchantAliasRepository merchantAliasRepository;
  private static final String MERCHANT_ALIAS_NOT_FOUND = "Merchant Alias not found";


  @Autowired
  public MerchantAliasService(MerchantAliasRepository merchantAliasRepository) {
    this.merchantAliasRepository = merchantAliasRepository;
  }


  public MerchantAlias createMerchantAlias(MerchantAlias merchantAlias) {
    verifyAliasIdIsUnique(merchantAlias.getAliasId());
    return merchantAliasRepository.save(merchantAlias);
  }

  public MerchantAlias updateMerchantAlias(MerchantAlias merchantAliasToUpdate,
      MerchantAlias updateMerchantAlias) {
    VisaBeanUtil.copyProperties(updateMerchantAlias, merchantAliasToUpdate);
    return merchantAliasRepository.save(merchantAliasToUpdate);
  }

  public MerchantAlias fetchByAliasId(String aliasId) {
    MerchantAlias merchantAlias = merchantAliasRepository.findOneByAliasId(aliasId);
    if (merchantAlias == null) {
      throw new NotFoundException(MERCHANT_ALIAS_NOT_FOUND);
    }
    return merchantAlias;
  }

  public Page<MerchantAlias> findAllMerchantAliases(MerchantSearchRequest request) {
    return merchantAliasRepository
        .findAll(request.getBooleanExpression(), request.getPaginationQuery());
  }

  private void verifyAliasIdIsUnique(String aliasId) throws ConflictException {
    MerchantAlias savedMerchantAlias = merchantAliasRepository.findOneByAliasId(aliasId);
    if (savedMerchantAlias != null) {
      throw new ConflictException("Merchant Alias already exist");
    }
  }

}
