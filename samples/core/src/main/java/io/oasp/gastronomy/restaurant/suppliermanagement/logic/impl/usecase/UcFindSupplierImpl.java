package io.oasp.gastronomy.restaurant.suppliermanagement.logic.impl.usecase;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import io.oasp.gastronomy.restaurant.general.logic.api.UseCase;
import io.oasp.gastronomy.restaurant.suppliermanagement.dataaccess.api.SupplierEntity;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.to.SupplierEto;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.to.SupplierSearchCriteriaTo;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.usecase.UcFindSupplier;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.base.usecase.AbstractSupplierUc;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;

/**
 * Use case implementation for searching, filtering and getting Suppliers
 */
@Named
@UseCase
@Validated
@Transactional
public class UcFindSupplierImpl extends AbstractSupplierUc implements UcFindSupplier {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UcFindSupplierImpl.class);

  @Override
  public SupplierEto findSupplier(Long id) {

    LOG.debug("Get Supplier with id {} from database.", id);
    return getBeanMapper().map(getSupplierDao().findOne(id), SupplierEto.class);
  }

  @Override
  public PaginatedListTo<SupplierEto> findSupplierEtos(SupplierSearchCriteriaTo criteria) {

    criteria.limitMaximumPageSize(MAXIMUM_HIT_LIMIT);
    PaginatedListTo<SupplierEntity> suppliers = getSupplierDao().findSuppliers(criteria);
    return mapPaginatedEntityList(suppliers, SupplierEto.class);
  }

}
