package grennite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
public class GrenniteTest {
    @Test
    public void testGrenniteInitialization() {
        Grennite grennite = new Grennite("./data/test_tasks.txt");
        assertNotNull(grennite, "Grennite is initialized properly.");
    }
}
