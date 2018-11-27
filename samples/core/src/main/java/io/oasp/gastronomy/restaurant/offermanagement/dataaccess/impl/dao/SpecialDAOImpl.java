package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.impl.dao;

import java.util.List;

import javax.inject.Named;

import io.oasp.gastronomy.restaurant.general.dataaccess.base.dao.ApplicationMasterDataDaoImpl;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.SpecialDAO;

/**
 * @author MNOWACKI
 *
 */
@Named
public class SpecialDAOImpl extends ApplicationMasterDataDaoImpl<SpecialEntity> implements SpecialDAO {

  @SuppressWarnings("javadoc")
  @Override
  protected Class<SpecialEntity> getEntityClass() {

    return SpecialEntity.class;
  }

  public List<SpecialEntity> findOffersFiltered() {

    return null;
  }
}