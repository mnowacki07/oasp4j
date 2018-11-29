package io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.usecase;

import java.util.List;

import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.to.SupplierEto;
import io.oasp.gastronomy.restaurant.suppliermanagement.logic.api.to.SupplierSearchCriteriaTo;
import io.oasp.module.jpa.common.api.to.PaginatedListTo;

public interface UcFindSupplier {

  /**
   * Returns a Supplier by its id 'id'.
   *
   * @param id The id 'id' of the Supplier.
   * @return The {@link SupplierEto} with id 'id'
   */
  SupplierEto findSupplier(Long id);

  /**
   * Returns a paginated list of Suppliers matching the search criteria.
   *
   * @param criteria the {@link SupplierSearchCriteriaTo}.
   * @return the {@link List} of matching {@link SupplierEto}s.
   */
  PaginatedListTo<SupplierEto> findSupplierEtos(SupplierSearchCriteriaTo criteria);

}
