package io.oasp.gastronomy.restaurant.offermanagement.logic.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.OfferEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.SpecialEntity;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.OfferDao;
import io.oasp.gastronomy.restaurant.offermanagement.dataaccess.api.dao.SpecialDao;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferCto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialEto;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.SpecialSearchCriteriaTo;
import io.oasp.module.beanmapping.common.api.BeanMapper;
import io.oasp.module.test.common.base.ModuleTest;

/**
 * This class tests the correct execution of the methods findOffer and findOfferCto belonging to the
 * {@link OffermanagementImpl}
 *
 */

public class OffermanagementImplTest extends ModuleTest {

  private static final long ID = 1;

  /**
   * The System Under Test (SUT)
   */

  private OffermanagementImpl offerManagementImpl;

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  @Mock
  private SpecialDao specialDao;

  @Mock
  private OfferDao offerDao;

  @Mock
  private BeanMapper beanMapper;

  /**
   * This method initializes the object {@link OffermanagementImpl} and assigns the mocked objects of the classes
   * {@link OfferDao} and {@link BeanMapper} to the attributes of the {@link OffermanagementImpl} object before tests,
   * if they are not null.
   */
  @Before
  public void init() {

    this.offerManagementImpl = new OffermanagementImpl();
    this.offerManagementImpl.setSpecialDao(this.specialDao);
    this.offerManagementImpl.setOfferDao(this.offerDao);
    this.offerManagementImpl.setBeanMapper(this.beanMapper);
  }

  /**
   * This method dereferences all object after each test
   */
  @After
  public void clean() {

    this.beanMapper = null;
    this.offerDao = null;
    this.offerManagementImpl = null;
  }

  /**
   * This method tests the execution of the findOffer method belonging to the {@link OffermanagementImpl} class
   */
  @Test
  public void findOffer() {

    // given
    OfferEntity offerEntity = mock(OfferEntity.class);
    OfferEto offerEto = new OfferEto();

    when(this.offerDao.findOne(ID)).thenReturn(offerEntity);
    when(this.beanMapper.map(offerEntity, OfferEto.class)).thenReturn(offerEto);

    // when
    OfferEto responseOfferEto = this.offerManagementImpl.findOffer(ID);

    // then
    assertThat(responseOfferEto).isNotNull();
    assertThat(responseOfferEto).isEqualTo(offerEto);
  }

  /**
   * This method tests the execution of the findOfferCto method belonging to the {@link OffermanagementImpl} class
   */
  @Test
  public void findOfferCto() {

    // given
    OfferCto offerCto = new OfferCto();
    OfferEto offerEto = new OfferEto();

    offerCto.setOffer(offerEto);
    OfferEntity offerEntity = mock(OfferEntity.class);

    when(this.offerDao.findOne(ID)).thenReturn(offerEntity);
    when(this.beanMapper.map(offerEntity, OfferEto.class)).thenReturn(offerEto);

    // when
    OfferCto responseOfferCto = this.offerManagementImpl.findOfferCto(ID);

    // then
    assertThat(responseOfferCto).isNotNull();
    assertThat(responseOfferCto.getOffer()).isEqualTo(offerEto);

  }

  @Test
  public void shouldFindSpecials() {

    SpecialEntity special = new SpecialEntity();
    SpecialSearchCriteriaTo searchCriteria = new SpecialSearchCriteriaTo();
    SpecialEto specialEto = new SpecialEto();

    List<SpecialEntity> specials = Collections.singletonList(special);
    when(this.specialDao.findActiveSpecials(searchCriteria)).thenReturn(specials);

    List<SpecialEto> specialEtos = Collections.singletonList(specialEto);
    when(this.beanMapper.mapList(specials, SpecialEto.class)).thenReturn(specialEtos);

    List<SpecialEto> activeSpecials = this.offerManagementImpl.getActiveSpecials(searchCriteria);

    assertThat(activeSpecials).isNotNull();
    assertThat(activeSpecials.get(0)).isEqualTo(specialEto);

  }

  @Test
  public void findSpecial() {

    // given
    SpecialEntity specialEntity = mock(SpecialEntity.class);
    SpecialEto specialEto = new SpecialEto();

    when(this.specialDao.findOne(ID)).thenReturn(specialEntity);
    when(this.beanMapper.map(specialEntity, SpecialEto.class)).thenReturn(specialEto);

    // when
    SpecialEto responseOfferEto = this.offerManagementImpl.findSpecial(ID);

    // then
    assertThat(responseOfferEto).isNotNull();
    assertThat(responseOfferEto).isEqualTo(specialEto);

  }

  @Test
  public void shouldSaveSpecial() {

    SpecialEto specialEto = new SpecialEto();
    SpecialEntity special = new SpecialEntity();

    when(this.beanMapper.map(special, SpecialEto.class)).thenReturn(specialEto);
    when(this.beanMapper.map(specialEto, SpecialEntity.class)).thenReturn(special);
    when(this.specialDao.save(special)).thenReturn(special);

    SpecialEto savedSpecial = this.offerManagementImpl.saveSpecial(specialEto);

    assertThat(savedSpecial).isEqualTo(specialEto);

  }

  @Test
  public void deleteSpecial() {

    this.offerManagementImpl.deleteSpecial(ID);

    Mockito.verify(this.specialDao).delete(ID);

  }

  public static <T extends List<?>> T cast(Object obj) {

    return (T) obj;
  }

}
