import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    @Test
    void addAndUniqueness() {
        ContactService svc = new ContactService();
        Contact c1 = new Contact("1", "Alice", "Smith", "1234567890", "456 Elm St");
        assertTrue(svc.addContact(c1));
        assertFalse(svc.addContact(c1)); // duplicate
    }

    @Test
    void deleteById() {
        ContactService svc = new ContactService();
        Contact c1 = new Contact("2", "Bob", "Brown", "1234567890", "789 Oak St");
        svc.addContact(c1);
        assertTrue(svc.deleteContact("2"));
        assertFalse(svc.deleteContact("2")); // already deleted
        assertFalse(svc.deleteContact("missing"));
    }

    @Test
    void updateAllFields() {
        ContactService svc = new ContactService();
        Contact c = new Contact("3", "Carol", "Jones", "1234567890", "321 Pine St");
        svc.addContact(c);

        assertTrue(svc.updateFirstName("3", "Carrie"));
        assertEquals("Carrie", c.getFirstName());

        assertTrue(svc.updateLastName("3", "Johnson"));
        assertEquals("Johnson", c.getLastName());

        assertTrue(svc.updatePhone("3", "0987654321"));
        assertEquals("0987654321", c.getPhone());

        assertTrue(svc.updateAddress("3", "654 Maple St"));
        assertEquals("654 Maple St", c.getAddress());
    }

    @Test
    void updateMissingIdFails() {
        ContactService svc = new ContactService();
        assertFalse(svc.updateFirstName("999", "X"));
        assertFalse(svc.updateLastName("999", "Y"));
        assertFalse(svc.updatePhone("999", "1234567890"));
        assertFalse(svc.updateAddress("999", "Addr"));
    }

    @Test
    void updateValidationsBubbleUp() {
        ContactService svc = new ContactService();
        Contact c = new Contact("4", "A", "B", "1234567890", "Addr");
        svc.addContact(c);
        assertThrows(IllegalArgumentException.class, () -> svc.updatePhone("4", "bad"));
        assertThrows(IllegalArgumentException.class, () -> svc.updateFirstName("4", "ABCDEFGHIJK"));
    }
}
