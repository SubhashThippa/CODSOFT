package AddressBook;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import java.awt.Color;

public class AddressBookGUI {

    public JFrame frame;
    public AddressBook_3 addressSys; // Added to manage contacts

    public AddressBookGUI() {
        initialize();
        addressSys = new AddressBook_3(); // Initialize the address book system
        addressSys.loadContactsFromFile(); // Load contacts only once

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddressBookGUI window = new AddressBookGUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void initialize() {
    	frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 165, 0));
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 222, 173));
        panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.setBounds(26, 26, 529, 81);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Address Book System");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 23));
        lblNewLabel.setBounds(131, 11, 332, 59);
        panel.add(lblNewLabel);

        JButton btnAdd = new JButton("Add Contacts");
        btnAdd.setBackground(new Color(0, 255, 255));
        btnAdd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        btnAdd.setBounds(52, 157, 183, 54);
        frame.getContentPane().add(btnAdd);


        
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open a dialog for adding contacts
                AddContactDialog addDialog = new AddContactDialog();
                addDialog.setVisible(true);
            }
        });
        JButton btnEdit = new JButton("Edit Contacts");
        btnEdit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        btnEdit.setBackground(Color.CYAN);
        btnEdit.setBounds(192, 363, 183, 54);
        frame.getContentPane().add(btnEdit);

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open a dialog for editing contacts
                EditContactDialog editDialog = new EditContactDialog(addressSys);
                editDialog.setVisible(true);
            }
        });
     // ... (Your existing code)

        JButton btnDisplay = new JButton("Display Contacts");
        btnDisplay.setBackground(new Color(0, 255, 255));
        btnDisplay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        btnDisplay.setBounds(52, 266, 183, 54);
        frame.getContentPane().add(btnDisplay);

        btnDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display all contacts

                ArrayList<Contact_3> contacts = addressSys.getAllContacts1();
                if (contacts.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No contacts found.");
                } else {
                    StringBuilder contactInfo = new StringBuilder("Contacts:\n");
                    for (Contact_3 contact : contacts) {
                        contactInfo.append(contact.toString()).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(null, contactInfo.toString());
                }
            }
        });




        JButton btnSearch = new JButton("Search Contacts");
        btnSearch.setBackground(new Color(0, 255, 255));
        btnSearch.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        btnSearch.setBounds(337, 157, 183, 54);
        frame.getContentPane().add(btnSearch);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                String name = JOptionPane.showInputDialog(null, "Enter name to search:");
                if (name != null && !name.isEmpty()) {
                    Contact_3 contact = addressSys.searchContact(name);
                    if (contact != null) {
                        JOptionPane.showMessageDialog(null, "Contact found:\n" + contact.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Contact not found.");
                    }
                }
            }
        });

        JButton btnDelete = new JButton("Delete Contacts");
        btnDelete.setBackground(new Color(0, 255, 255));
        btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        btnDelete.setBounds(337, 266, 183, 54);
        frame.getContentPane().add(btnDelete);

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                String name = JOptionPane.showInputDialog(null, "Enter name to delete:");
                if (name != null && !name.isEmpty()) {
                    if (addressSys.removeContact(name)) {
                        JOptionPane.showMessageDialog(null, "Contact deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete contact.");
                    }
                }
            }
        });

       


    }

    // Additional classes for custom dialogs
     class AddContactDialog extends JFrame {
    	

    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public JTextField nameField;
    	public JTextField phoneField;
    	public JTextField emailField;
    	public JTextField addressField;

    	    public AddContactDialog() {
    	        setTitle("Add Contact");
    	        setSize(400, 300);
    	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	        getContentPane().setLayout(null);

    	        JPanel panel = new JPanel();
    	        panel.setBounds(0, 0, 384, 261);
    	        getContentPane().add(panel);
    	        panel.setLayout(null);

    	        JLabel lblNewLabel = new JLabel("Name:");
    	        lblNewLabel.setBounds(23, 26, 46, 14);
    	        panel.add(lblNewLabel);

    	        nameField = new JTextField();
    	        nameField.setBounds(103, 23, 220, 20);
    	        panel.add(nameField);
    	        nameField.setColumns(10);
    	        
    	        JLabel lblPhone = new JLabel("Phone:");
    	        lblPhone.setBounds(23, 58, 70, 14); // Adjust the bounds as needed
    	        panel.add(lblPhone);

    	        phoneField = new JTextField();
    	        phoneField.setBounds(103, 58, 220, 20);
    	        panel.add(phoneField);
    	        phoneField.setColumns(10);

    	        JLabel lblEmail = new JLabel("Email:");
    	        lblEmail.setBounds(23, 94, 46, 14);
    	        panel.add(lblEmail);

    	        emailField = new JTextField();
    	        emailField.setBounds(103, 91, 220, 20);
    	        panel.add(emailField);
    	        emailField.setColumns(10);

    	        JLabel lblAddress = new JLabel("Address:");
    	        lblAddress.setBounds(23, 130, 70, 14);
    	        panel.add(lblAddress);

    	        addressField = new JTextField();
    	        addressField.setBounds(103, 127, 220, 20);
    	        panel.add(addressField);
    	        addressField.setColumns(10);

    	       

    	        JButton addButton = new JButton("Add");
    	        addButton.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent e) {

    	                String name = nameField.getText();
    	                String phone = phoneField.getText();
    	                String email = emailField.getText();
    	                String address = addressField.getText();

    	                if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || address.isEmpty()) {
    	                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
    	                } else {
    	                    Contact_3 contact = new Contact_3(name, phone, email, address);
    	                    if (addressSys.addContact(contact)) {
    	                        JOptionPane.showMessageDialog(null, "Contact added successfully.");
    	                        dispose();
    	                    } else {
    	                        JOptionPane.showMessageDialog(null, "Failed to add the contact.");
    	                    }
    	                }
    	            }
    	        });
    	        addButton.setBounds(103, 194, 89, 23);
    	        panel.add(addButton);

    	        JButton exitButton = new JButton("Exit");
    	        exitButton.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent e) {
    	                dispose();
    	            }
    	        });
    	        exitButton.setBounds(234, 194, 89, 23);
    	        panel.add(exitButton);
    	    }
    	}
    }

class EditContactDialog extends JFrame {
    public AddressBook_3 addressSys;

    public JTextField nameField;
    public JTextField newPhoneField;
    public JTextField newEmailField;
    public JTextField newAddressField;

    public EditContactDialog(AddressBook_3 addressSys) {
        this.addressSys = addressSys;

        setTitle("Edit Contact");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 384, 261);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name:");
        lblNewLabel.setBounds(23, 26, 46, 14);
        panel.add(lblNewLabel);

        nameField = new JTextField();
        nameField.setBounds(103, 23, 220, 20);
        panel.add(nameField);
        nameField.setColumns(10);

        JLabel lblNewPhone = new JLabel("New Phone:");
        lblNewPhone.setBounds(23, 66, 70, 14);
        panel.add(lblNewPhone);

        newPhoneField = new JTextField();
        newPhoneField.setBounds(103, 63, 220, 20);
        panel.add(newPhoneField);
        newPhoneField.setColumns(10);

        JLabel lblNewEmail = new JLabel("New Email:");
        lblNewEmail.setBounds(23, 106, 70, 14);
        panel.add(lblNewEmail);

        newEmailField = new JTextField();
        newEmailField.setBounds(103, 103, 220, 20);
        panel.add(newEmailField);
        newEmailField.setColumns(10);

        JLabel lblNewAddress = new JLabel("New Address:");
        lblNewAddress.setBounds(23, 146, 70, 14);
        panel.add(lblNewAddress);

        newAddressField = new JTextField();
        newAddressField.setBounds(103, 143, 220, 20);
        panel.add(newAddressField);
        newAddressField.setColumns(10);
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String newPhone = newPhoneField.getText();
                String newEmail = newEmailField.getText();
                String newAddress = newAddressField.getText();

                if (name.isEmpty() || newPhone.isEmpty() || newEmail.isEmpty() || newAddress.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else {
                    addressSys.loadContactsFromFile();

                    Contact_3 newContact = new Contact_3(name, newPhone, newEmail, newAddress);
                    if (addressSys.updateContact(name, newContact)) {
                        JOptionPane.showMessageDialog(null, "Contact updated successfully.");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update the contact.");
                    }
                }
            }
        });
        updateButton.setBounds(103, 194, 89, 23);
        panel.add(updateButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        exitButton.setBounds(234, 194, 89, 23);
        panel.add(exitButton);
    }
}


 class Contact_3 {
	public String name;
	public String phone;
	public String email;
	public String address;

    public Contact_3(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Getters and setters for each field

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
               "Phone: " + phone + "\n" +
               "Email: " + email + "\n" +
               "Address: " + address;
    }
}


 class AddressBook_3 {

	public ArrayList<Contact_3> contacts;

    public AddressBook_3() {
        contacts = new ArrayList<>();
    }

    public boolean addContact(Contact_3 contact) {
        try {
            contacts.add(contact);
            saveContactsToFile();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateContact(String name, Contact_3 newContact) {
        try {
            Contact_3 existingContact = searchContact(name);
            if (existingContact != null) {
                contacts.remove(existingContact);
                contacts.add(newContact);
                saveContactsToFile();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeContact(String name) {
        try {
            Contact_3 contact = searchContact(name);
            if (contact != null) {
                contacts.remove(contact);
                saveContactsToFile();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Contact_3 searchContact(String name) {
        for (Contact_3 contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }


    
 // Save contacts to the file
    public void saveContactsToFile() {
        try {
            Contact_3 c;
            String line;
            FileWriter fw = new FileWriter("C:\\Users\\subha\\eclipse-workspace\\subhash\\codsoft\\AddressBook\\contacts1.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < contacts.size(); i++) {
                c = (Contact_3) contacts.get(i);
                line = c.name + "," + c.phone + "," + c.email + "," + c.address;
                pw.println(line);
            }
            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }

    // Load contacts from the file
    public void loadContactsFromFile() {
        String tokens[];
        String name, phone, email, address;
        try {
            FileReader fr = new FileReader("C:\\Users\\subha\\eclipse-workspace\\subhash\\codsoft\\AddressBook\\contacts1.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                tokens = line.split(",");
                name = tokens[0];
                phone = tokens[1];
                email = tokens[2];
                address = tokens[3];
                Contact_3 c = new Contact_3(name, phone, email, address);
                contacts.add(c);
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }


    public ArrayList<Contact_3> getAllContacts1() {
        return contacts;
    }
}
 
