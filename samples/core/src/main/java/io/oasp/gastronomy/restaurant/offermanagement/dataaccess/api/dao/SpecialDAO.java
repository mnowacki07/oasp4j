package io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao;

import io.oasp.gastronomy.restaurant.general.dataaccess.api.dao.ApplicationDao;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.module.jpa.dataaccess.api.MasterDataDao;

/**
 * @author MNOWACKI
 *
 */
public interface SpecialDAO extends ApplicationDao<SpecialEntity>, MasterDataDao<SpecialEntity> {

}
