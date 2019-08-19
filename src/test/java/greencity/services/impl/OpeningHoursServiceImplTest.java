package greencity.services.impl;

import static org.junit.Assert.*;

import greencity.GreenCityApplication;
import greencity.entities.OpeningHours;
import greencity.entities.enums.WeekDay;
import greencity.exceptions.NotFoundException;
import greencity.services.OpeningHoursService;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GreenCityApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OpeningHoursServiceImplTest {
    @Autowired private OpeningHoursService openingHoursService;

    @Test
    public void findAllTest() {
        List<OpeningHours> genericEntities = new ArrayList<>();
        List<OpeningHours> foundEntities;

        OpeningHours openingHours1 =
                OpeningHours.builder()
                        .openTime(LocalTime.of(9, 0))
                        .closeTime(LocalTime.of(18, 0))
                        .weekDay(WeekDay.MONDAY)
                        .build();

        OpeningHours openingHours2 =
                OpeningHours.builder()
                        .openTime(LocalTime.of(9, 0))
                        .closeTime(LocalTime.of(18, 0))
                        .weekDay(WeekDay.TUESDAY)
                        .build();

        genericEntities.add(openingHours1);
        genericEntities.add(openingHours2);

        openingHoursService.save(openingHours1);
        openingHoursService.save(openingHours2);

        foundEntities = openingHoursService.findAll();

        assertNotNull(foundEntities);
        assertEquals(genericEntities, foundEntities);
    }

    @Test
    public void findByIdTest() {
        OpeningHours genericEntity =
                OpeningHours.builder()
                        .openTime(LocalTime.of(9, 0))
                        .closeTime(LocalTime.of(18, 0))
                        .weekDay(WeekDay.MONDAY)
                        .build();
        openingHoursService.save(genericEntity);

        OpeningHours foundEntity = openingHoursService.findById(genericEntity.getId());

        assertNotNull(foundEntity);
        assertEquals(genericEntity, foundEntity);
    }

    @Test
    public void saveTest() {
        OpeningHours genericEntity =
                OpeningHours.builder()
                        .openTime(LocalTime.of(9, 0))
                        .closeTime(LocalTime.of(18, 0))
                        .weekDay(WeekDay.MONDAY)
                        .build();
        openingHoursService.save(genericEntity);

        OpeningHours foundEntity = openingHoursService.findById(1L);

        assertEquals(genericEntity, foundEntity);
    }

    @Test(expected = NotFoundException.class)
    public void deleteTest() {
        OpeningHours genericEntity =
                OpeningHours.builder()
                        .openTime(LocalTime.of(9, 0))
                        .closeTime(LocalTime.of(18, 0))
                        .weekDay(WeekDay.MONDAY)
                        .build();
        openingHoursService.save(genericEntity);
        openingHoursService.delete(openingHoursService.findById(1L));

        openingHoursService.findById(1L);
    }

    @Test
    public void updateTest() {
        OpeningHours genericEntity =
                OpeningHours.builder()
                        .openTime(LocalTime.of(9, 0))
                        .closeTime(LocalTime.of(18, 0))
                        .weekDay(WeekDay.MONDAY)
                        .build();
        openingHoursService.save(genericEntity);

        OpeningHours updated =
                OpeningHours.builder()
                        .openTime(LocalTime.of(9, 0))
                        .closeTime(LocalTime.of(19, 0))
                        .weekDay(WeekDay.MONDAY)
                        .build();
        openingHoursService.update(1L, updated);

        OpeningHours foundEntity = openingHoursService.findById(1L);

        assertEquals(updated.getCloseTime(), foundEntity.getCloseTime());
    }
}
