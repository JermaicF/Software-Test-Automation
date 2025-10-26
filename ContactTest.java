import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void validContactCreation() {
        Contact c = new Contact("12550", "Tony", "Fuentes", "3476856175", "Sunshine Rd");
        assertEquals("12550", c.getContactId());
        assertEquals("Tony", c.getFirstName());
        assertEquals("Fuentes", c.getLastName());
        assertEquals("3476856175", c.getPhone());
        assertEquals("Sunshine Rd", c.getAddress());
    }

    @Test
    void idConstraints() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "A", "B", "1234567890", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("12345678901", "A", "B", "1234567890", "Addr")); // 11
    }

    @Test
    void nameConstraints() {
        String over10 = "ABCDEFGHIJK"; // 11
        assertThrows(IllegalArgumentException.class, () -> new Contact("ID", null, "B", "1234567890", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("ID", over10, "B", "1234567890", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("ID", "A", null, "1234567890", "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("ID", "A", over10, "1234567890", "Addr"));
    }

    @Test
    void phoneConstraints() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("ID", "A", "B", null, "Addr"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("ID", "A", "B", "123456789", "Addr"));   // 9
        assertThrows(IllegalArgumentException.class, () -> new Contact("ID", "A", "B", "12345678901", "Addr")); // 11
        assertThrows(IllegalArgumentException.class, () -> new Contact("ID", "A", "B", "123-456-7890", "Addr")); // non-digits
    }

    @Test
    void addressConstraints() {
        String over30 = "1234567890123456789012345678901"; // 31
        assertThrows(IllegalArgumentException.class, () -> new Contact("ID", "A", "B", "1234567890", null));
        assertThrows(IllegalArgumentException.class, () -> new Contact("ID", "A", "B", "1234567890", over30));
    }

    @Test
    void settersEnforceValidation() {
        Contact c = new Contact("ID", "First", "Last", "1234567890", "Somewhere");
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName("ABCDEFGHIJK"));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("abc"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("x".repeat(31)));
    }
}
