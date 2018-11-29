package io.oasp.gastronomy.restaurant.suppliermanagement.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.oasp.gastronomy.restaurant.general.common.api.constants.PermissionConstants;
import io.oasp.gastronomy.restaurant.general.logic.base.AbstractComponentFacade;
import io.oasp.gastronomy.restaurant.suppliermanagement.dataaccess.api.SupplierEntity;
import io.oasp.gastronomy.restaurant.suppliermanagement.dataaccess.api.dao.SupplierDao;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.Suppliermanagement;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.to.SupplierCto;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.to.SupplierEto;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.to.SupplierSearchCriteriaTo;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.usecase.UcFindSupplier;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.usecase.UcManageSupplier;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;
import io.oasp.module.jpa.common.api.to.PaginationResultTo;

/**
 * Implementation of component interface of suppliermanagement
 */
@Named
public class SuppliermanagementImpl extends AbstractComponentFacade implements Suppliermanagement {

  @Inject
  private UcFindSupplier ucFindSupplier;

  @Inject
  private UcManageSupplier ucManageSupplier;

  /**
   * Logger instance.
   */
  private static final Logger LOG = LoggerFactory.getLogger(SuppliermanagementImpl.class);

  /**
   * @see #getSupplierDao()
   */
  @Inject
  private SupplierDao supplierDao;

  /**
   * The constructor.
   */
  public SuppliermanagementImpl() {

    super();
  }

  @Override
  @RolesAllowed(PermissionConstants.FIND_SUPPLIERS)
  public SupplierEto findSupplier(Long id) {

    return this.ucFindSupplier.findSupplier(id);
  }

  @Override
  @RolesAllowed(PermissionConstants.FIND_SUPPLIERS)
  public PaginatedListTo<SupplierEto> findSupplierEtos(SupplierSearchCriteriaTo criteria) {

    return this.ucFindSupplier.findSupplierEtos(criteria);
  }

  @Override
  public SupplierEto saveSupplier(SupplierEto supplier) {

    return this.ucManageSupplier.saveSupplier(supplier);
  }

  @Override
  public boolean deleteSupplier(Long id) {

    return this.ucManageSupplier.deleteSupplier(id);
  }

  /**
   * Returns the field 'supplierDao'.
   *
   * @return the {@link SupplierDao} instance.
   */
  public SupplierDao getSupplierDao() {

    return this.supplierDao;
  }

  @Override
  public SupplierCto findSupplierCto(Long id) {

    LOG.debug("Get SupplierCto with id {} from database.", id);
    SupplierEntity entity = getSupplierDao().findOne(id);
    SupplierCto cto = new SupplierCto();
    cto.setSupplier(getBeanMapper().map(entity, SupplierEto.class));

    return cto;
  }

  @Override
  public PaginatedListTo<SupplierCto> findSupplierCtos(SupplierSearchCriteriaTo criteria) {

    criteria.limitMaximumPageSize(MAXIMUM_HIT_LIMIT);
    PaginatedListTo<SupplierEntity> suppliers = getSupplierDao().findSuppliers(criteria);
    List<SupplierCto> ctos = new ArrayList<>();
    for (SupplierEntity entity : suppliers.getResult()) {
      SupplierCto cto = new SupplierCto();
      cto.setSupplier(getBeanMapper().map(entity, SupplierEto.class));
      ctos.add(cto);

    }
    PaginationResultTo pagResultTo = new PaginationResultTo(criteria.getPagination(), (long) ctos.size());
    PaginatedListTo<SupplierCto> pagListTo = new PaginatedListTo(ctos, pagResultTo);
    return pagListTo;
  }

}
