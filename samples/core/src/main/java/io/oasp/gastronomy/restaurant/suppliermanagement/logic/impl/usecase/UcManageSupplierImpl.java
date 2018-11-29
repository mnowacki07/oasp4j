package io.oasp.gastronomy.restaurant.suppliermanagement.logic.impl.usecase;

import java.util.Objects;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import io.oasp.gastronomy.restaurant.general.logic.api.UseCase;
import io.oasp.gastronomy.restaurant.suppliermanagement.dataaccess.api.SupplierEntity;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.to.SupplierEto;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.usecase.UcManageSupplier;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.base.usecase.AbstractSupplierUc;

/**
 * Use case implementation for modifying and deleting Suppliers
 */
@Named
@UseCase
@Validated
@Transactional
public class UcManageSupplierImpl extends AbstractSupplierUc implements UcManageSupplier {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UcManageSupplierImpl.class);

  @Override
  public boolean deleteSupplier(Long supplierId) {

    SupplierEntity supplier = getSupplierDao().find(supplierId);
    getSupplierDao().delete(supplier);
    LOG.debug("The supplier with id '{}' has been deleted.", supplierId);
    return true;
  }

  @Override
  public SupplierEto saveSupplier(SupplierEto supplier) {

    Objects.requireNonNull(supplier, "supplier");

    SupplierEntity supplierEntity = getBeanMapper().map(supplier, SupplierEntity.class);

    // initialize, validate supplierEntity here if necessary
    SupplierEntity resultEntity = getSupplierDao().save(supplierEntity);
    LOG.debug("Supplier with id '{}' has been created.", resultEntity.getId());
    return getBeanMapper().map(resultEntity, SupplierEto.class);
  }
}
