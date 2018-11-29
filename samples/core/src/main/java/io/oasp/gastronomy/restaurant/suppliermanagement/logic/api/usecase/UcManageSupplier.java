package io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.usecase;

import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.to.SupplierEto;

/**
 * Interface of UcManageSupplier to centralize documentation and signatures of methods.
 */
public interface UcManageSupplier {

  /**
   * Deletes a supplier from the database by its id 'supplierId'.
   *
   * @param supplierId Id of the supplier to delete
   * @return boolean <code>true</code> if the supplier can be deleted, <code>false</code> otherwise
   */
  boolean deleteSupplier(Long supplierId);

  /**
   * Saves a supplier and store it in the database.
   *
   * @param supplier the {@link SupplierEto} to create.
   * @return the new {@link SupplierEto} that has been saved with ID and version.
   */
  SupplierEto saveSupplier(SupplierEto supplier);

}
