package net.troja.picoro.db;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DbIntegrationTest {
    @Autowired
    private RoastingEntityRepository roastingEntityRepository;
    @Autowired
    private RoastingValuesEntityRepository roastingValuesEntityRepository;
    @Autowired
    private RoastProfileEntityRepository roastProfileEntityRepository;

    @Test
    public void init() {
        final RoastingEntity roast = new RoastingEntity("Thailand", "Super Bean", LocalDateTime.now(), 2345, 14.5f, "No comments");
        final RoastingValuesEntity value = new RoastingValuesEntity(245, 29.3f, 243.7f, 77, 80, false);
        roast.addValue(value);
        final RoastingEntity roastingEntity = roastingEntityRepository.save(roast);
    }
}
