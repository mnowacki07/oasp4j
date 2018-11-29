package io.oasp.gastronomy.restaurant.suppliermanagement.logic.base.usecase;

import javax.inject.Inject;

import io.oasp.gastronomy.restaurant.general.logic.base.AbstractUc;
import io.oasp.gastronomy.restaurant.suppliermanagement.dataaccess.api.dao.SupplierDao;

/**
 * Abstract use case for Suppliers, which provides access to the commonly necessary data access objects.
 */
public class AbstractSupplierUc extends AbstractUc {

  /** @see #getSupplierDao() */
  @Inject
  private SupplierDao supplierDao;

  /**
   * Returns the field 'supplierDao'.
   * 
   * @return the {@link SupplierDao} instance.
   */
  public SupplierDao getSupplierDao() {

    return this.supplierDao;
  }

}
