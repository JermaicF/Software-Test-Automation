import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    public boolean addContact(Contact contact) {
        if (contacts.containsKey(contact.getContactId())) return false;
        contacts.put(contact.getContactId(), contact);
        return true;
    }

    public boolean deleteContact(String contactId) {
        return contacts.remove(contactId) != null;
    }

    public boolean updateFirstName(String contactId, String firstName) {
        Contact c = contacts.get(contactId);
        if (c == null) return false;
        c.setFirstName(firstName);
        return true;
    }

    public boolean updateLastName(String contactId, String lastName) {
        Contact c = contacts.get(contactId);
        if (c == null) return false;
        c.setLastName(lastName);
        return true;
    }

    public boolean updatePhone(String contactId, String phone) {
        Contact c = contacts.get(contactId);
        if (c == null) return false;
        c.setPhone(phone);
        return true;
    }

    public boolean updateAddress(String contactId, String address) {
        Contact c = contacts.get(contactId);
        if (c == null) return false;
        c.setAddress(address);
        return true;
    }
}
