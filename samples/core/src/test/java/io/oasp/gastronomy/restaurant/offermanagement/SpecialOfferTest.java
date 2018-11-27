package io.oasp.gastronomy.restaurant.offermanagement;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import io.oasp.gastronomy.restaurant.SpringBootApp;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.SpecialDAO;
import io.oasp.module.test.common.base.ComponentTest;

/**
 * @author MNOWACKI
 *
 */

@Transactional
@SpringBootTest(classes = { SpringBootApp.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpecialOfferTest extends ComponentTest {

  // @Autowired
  // OfferDao offerDao;

  @Inject
  SpecialDAO specialDao;

  /**

   */
  @Test
  public void addTest() {

    SpecialEntity special = new SpecialEntity();
    long a = 5;
    special.setId(a);
    this.specialDao.save(special);
    assertTrue(this.specialDao.exists(a));
  }

}
